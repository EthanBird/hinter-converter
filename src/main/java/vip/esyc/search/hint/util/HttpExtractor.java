package vip.esyc.search.hint.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * @ProjectName: hinter-converter
 * @Package: vip.esyc.search.hint.util
 * @ClassName: HttpExtract
 * @Author: Syicong
 * @Description:
 * @Date: 2022/7/5 18:46
 * @Version: 1.0
 */
public class HttpExtractor {
    public static JSONObject doGet(String baseUrl, String query) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        String url = baseUrl + query;
        GetMethod getMethod = new GetMethod(url);
        JSONObject jsonResponse;
        int statusCode = 404;
        try {
            statusCode = httpClient.executeMethod(getMethod);
            jsonResponse = JSON.parseObject(getMethod.getResponseBody());
        } catch (HttpException e) {
            String msg = "{'statusCode':'"+statusCode+"','status':'err','hint':'Check URL!', 'exception':'HttpException'}";
            jsonResponse = JSON.parseObject(msg);
        } catch (IOException e) {
            String msg = "{'statusCode':'"+statusCode+"','status':'err','hint':'Check Network!', 'exception':'IOException'}";
            jsonResponse = JSON.parseObject(msg);
        } finally {
            getMethod.releaseConnection();
        }
        return jsonResponse;
    }
}
