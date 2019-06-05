package com.xupt.common.untils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author maxu
 * @date 2019/6/5
 */
public class HttpClientUtils {
    public static final String GET = "get";
    public static final String POST = "post";
    public static final String REQUEST_HEADER_CONNECTION = "keep_alive";
    public static final String REQUEST_HEADER_USER_AGENT = "keep_alive";


    public static String doGet(String url) {
        return createHttpClient(url, GET, null);
    }
    public static String doGet(String url,String cookie) {
        return createHttpClient(url, GET,cookie, null);
    }
    public static String doPost(String url,String cookie,BasicNameValuePair ... params) {
        return createHttpClient(url, POST, cookie, params);
    }
    public static String doPost(String url,BasicNameValuePair ... params) {
        return createHttpClient(url, POST, null, params);
    }


    private static String createHttpClient(String url, String type, BasicNameValuePair ... params)  {
        return createHttpClient(url, type, null, params);
    }

    private static String createHttpClient(String url, String type, String cookie,BasicNameValuePair ... params)  {


        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpGet  httpGet = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            if (GET.equals(type)) {
                httpGet = new HttpGet(url);
                httpGet.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                httpGet.setHeader("Cookie", cookie);
                response = httpClient.execute(httpGet);
            } else if (POST.equals(type)) {
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                httpPost.setHeader("Cookie", cookie);
                if (params != null && params.length > 0) {
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));
                }
                response = httpClient.execute(httpPost);

            }
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e) {

        }finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
