package vip.esyc.search.hint.handler.etl;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: hinter-converter
 * @Package: vip.esyc.search.hint.handler.etl
 * @ClassName: MavenHintETLHandler
 * @Author: Syicong
 * @Description:
 * @Date: 2022/7/5 19:37
 * @Version: 1.0
 */
public class MavenHintETLHandler extends AbstractHintETLHandler {
    private static final String BASE_URL = "https://search.maven.org/solrsearch/select?q=";

    private MavenHintETLHandler(){
        super(BASE_URL);
    }

    @Override
    protected JSONArray transform(JSONObject extractedData) {
        List<String> list = new ArrayList<>();
        JSONObject response = (JSONObject) extractedData.get("response");
        JSONArray docs = response.getJSONArray("docs");
        for(Object e : docs){
            JSONObject line = (JSONObject) e;
            list.add((String.valueOf("'"+line.get("id")+"'")));
        }
        System.out.println(list.toString());
        return JSONArray.parseArray("["+list.toString()+"]");
    }

    public static MavenHintETLHandler getHandler() {
        return InnerClass.INSTANCE;
    }
    private static class InnerClass{
        private static MavenHintETLHandler INSTANCE = new MavenHintETLHandler();
    }
}
