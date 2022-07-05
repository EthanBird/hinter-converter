package vip.esyc.search.hint.handler.etl;



import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import vip.esyc.search.hint.util.HttpExtractor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: hinter-converter
 * @Package: vip.esyc.search.hint.handler.etl
 * @ClassName: AbstractHintETLHandler
 * @Author: Syicong
 * @Description:
 * @Date: 2022/7/5 17:10
 * @Version: 1.0
 */
public class AbstractHintETLHandler implements HintETLHandler{
    private String base_url = "https://www.baidu.com/sugrec?prod=pc&wd=";
    AbstractHintETLHandler() {

    }
    AbstractHintETLHandler(String baseUrl) {
        this.base_url = baseUrl;
    }

    private JSONObject extract(String query) {
        String queryEncode = URLEncoder.encode(query, StandardCharsets.UTF_8);
        return base_url.equals("") ? JSONObject.parseObject(query) : HttpExtractor.doGet(base_url, queryEncode);
    }

    protected JSONArray transform(JSONObject extractedData) {
        List<String> list = new ArrayList<>();
        JSONArray g = extractedData.getJSONArray("g");
        for(Object e : g){
            JSONObject line = (JSONObject) e;
            list.add((String.valueOf("'"+line.get("q")+"'")));
        }
        System.out.println(list.toString());
        return JSONArray.parseArray("["+list.toString()+"]");
    }


    private String load(JSONArray transformedData) {
        return transformedData.toString();
    }

    @Override
    public String doEtl(String query) {
        JSONObject data = extract(query);
        JSONArray t_data = transform(data);
        t_data.add(0, query);
        return load(t_data);
    }

    public static AbstractHintETLHandler getHandler() {
        return InnerClass.INSTANCE;
    }
    private static class InnerClass{
        private static AbstractHintETLHandler INSTANCE = new AbstractHintETLHandler();
    }
}
