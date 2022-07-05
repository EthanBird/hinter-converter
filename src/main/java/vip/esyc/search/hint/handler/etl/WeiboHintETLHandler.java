package vip.esyc.search.hint.handler.etl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: hinter-converter
 * @Package: vip.esyc.search.hint.handler.etl
 * @ClassName: JDKHintETLHandler
 * @Author: Syicong
 * @Description:
 * @Date: 2022/7/5 17:20
 * @Version: 1.0
 */
public class WeiboHintETLHandler extends AbstractHintETLHandler {
    private static final String BASE_URL = "https://s.weibo.com/ajax/topsuggest.php?outjson=1&uid=7333268482&key=";
    private WeiboHintETLHandler(){
        super(BASE_URL);
    }
    @Override
    protected JSONArray transform(JSONObject extractedData) {
        List<String> list = new ArrayList<>();
        JSONArray queries = extractedData.getJSONArray("querys");
        for(Object e : queries){
            JSONObject line = (JSONObject) e;
            list.add((String.valueOf("'"+line.get("key")+"'")));
        }
        JSONArray user = extractedData.getJSONArray("user");
        for(Object e : user){
            JSONObject line = (JSONObject) e;
            list.add((String.valueOf("'"+line.get("u_name")+"'")));
        }
        System.out.println(list.toString());
        return JSONArray.parseArray("["+list.toString()+"]");

    }

    public static WeiboHintETLHandler getHandler() {
        return InnerClass.INSTANCE;
    }
    private static class InnerClass{
        private static WeiboHintETLHandler INSTANCE = new WeiboHintETLHandler();
    }
}
