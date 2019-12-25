package top.snailclimb.util;

import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class HttpClient {
    private org.apache.http.client.HttpClient client = HttpClients.custom().build();
    private Gson gson = new Gson();


    private Header JSON[] = {
            new BasicHeader("Content-type", "application/json"),
            new BasicHeader("Accept", "application/json")
    };

    public HttpResponse post(String url, Object dto) throws IOException {
        HttpPost postRequest = new HttpPost(url);
        postRequest.setHeaders(JSON);
        postRequest.setEntity(new StringEntity(gson.toJson(dto), StandardCharsets.UTF_8));
        return client.execute(postRequest);
    }

    public HttpResponse get(String url) throws IOException {
        HttpGet getRequest = new HttpGet(url);
        getRequest.setHeaders(JSON);
        return client.execute(getRequest);
    }

    public HttpResponse put(String url, Object dto) throws IOException {
        HttpPut putRequest = new HttpPut(url);
        putRequest.setHeaders(JSON);
        putRequest.setEntity(new StringEntity(gson.toJson(dto), StandardCharsets.UTF_8));
        return client.execute(putRequest);
    }

    public HttpResponse delete(String url) throws IOException {
        HttpDelete deleteRequest = new HttpDelete(url);
        deleteRequest.setHeaders(JSON);
        return client.execute(deleteRequest);
    }
}
