package top.snailclimb.dubboconsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import top.snailclimb.util.HttpClient;

import java.util.Iterator;

public class QueryOrder extends HttpClient {

    @Test(description = "正常场景：传入正确的orderId，调用接口，返回OrderInfo，检查返回字段")
    public void getOrderById_CorrectId() throws Exception {
        Long orderId = 1l;
        String Order_URL = "http://127.0.0.1:8330/order" + '/' + orderId;
        HttpResponse response1 = get(Order_URL);
        HttpEntity entity = response1.getEntity();
        String jsonStr = EntityUtils.toString(entity);
        JSONObject responseBody = new JSONObject(jsonStr); //将返回结果json格式化
        Assert.assertEquals(responseBody.get("orderId").toString(),"1");
        Assert.assertEquals(responseBody.get("customerAddress").toString(),"武汉市");
        Assert.assertEquals(responseBody.get("customerName").toString(),"张三");

    }

    @Test(description = "正常场景：传入不存在的orderId，调用接口，返回errorCode")
    public void getOrderById_InvalidId() throws Exception {
        Long orderId = -1L;
        String Order_URL = "http://127.0.0.1:8330/order" + '/' + orderId;
        HttpResponse response1 = get(Order_URL);
        HttpEntity entity = response1.getEntity();
        String jsonStr = EntityUtils.toString(entity);
        JSONObject responseBody = new JSONObject(jsonStr); //将返回结果json格式化
        Assert.assertEquals(responseBody.get("errorCode").toString(),"10001");

    }

    @Test(description = "使用重写的httpClient方法调用")
    public void testGet3() throws Exception {
        String orderId = "1";
        String Order_URL = "http://127.0.0.1:8330/order" + '/' + orderId;
        String response = requestGet(Order_URL);
        System.out.println("返回结果：" + response);

    }

    private String requestGet(String urlWithParams) throws Exception {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        HttpGet httpget = new HttpGet(urlWithParams);
        //配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(50)
                .setConnectTimeout(50)
                .setSocketTimeout(50).build();
        httpget.setConfig(requestConfig);

        CloseableHttpResponse response = httpclient.execute(httpget);
        System.out.println("响应码StatusCode -> " + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        String jsonStr = EntityUtils.toString(entity);//, "utf-8");

        char c = jsonStr.trim().charAt(0);
        if ('[' == c) {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Iterator ite = jsonObj.keys();
                while (ite.hasNext()) {
                    String key = (String) ite.next();
                    System.out.println("key=:" + key + ",value=:" + jsonObj.getString(key));
                }
            }
        } else if ('{' == c) {
            JSONObject jsonObj1 = new JSONObject(jsonStr);
            Iterator ite = jsonObj1.keys();
            while (ite.hasNext()) {
                String key = (String) ite.next();
                System.out.println("解析后的数据：key=" + key + ",value=" + jsonObj1.getString(key));
            }


        }

        httpget.releaseConnection();
        return jsonStr;
    }


}
