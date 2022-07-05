package vip.esyc.search.hint.handler.etl;


import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONArray;

import java.util.*;


/**
 * @ProjectName: hinter-converter
 * @Package: vip.esyc.search.hint.handler.etl.handler
 * @ClassName: BilibiliHintETLHandler
 * @Author: Syicong
 * @Description:
 * @Date: 2022/7/5 16:18
 * @Version: 1.0
 */
public class BilibiliHintETLHandler extends AbstractHintETLHandler {
    private static final String BASE_URL = "https://s.search.bilibili.com/main/suggest?term=";

    private BilibiliHintETLHandler(){
        super(BASE_URL);
    }

    @Override
    protected JSONArray transform(JSONObject extractedData) {
        List<String> list = new ArrayList<>(10);
        for (Map.Entry<String, Object> stringObjectEntry : extractedData.entrySet()) {
            JSONObject line =  (JSONObject) ((Map.Entry) stringObjectEntry).getValue();
            list.add((String.valueOf("'"+line.get("name")+"'")));
        }
        System.out.println(list.toString());
        return JSONArray.parseArray("["+list.toString()+"]");
    }

    public static BilibiliHintETLHandler getHandler() {
        return InnerClass.INSTANCE;
    }
    private static class InnerClass{
        private static BilibiliHintETLHandler INSTANCE = new BilibiliHintETLHandler();
    }
}
