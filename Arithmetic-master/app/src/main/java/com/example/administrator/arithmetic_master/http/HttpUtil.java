package com.example.administrator.arithmetic_master.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @author guoqingyun
 * 作用：处理网络请求
 * */
public class HttpUtil extends Thread {
    private final static String urlPrefix = "http://172.23.25.51:8080/AppServer/";
    Handler handler;
    String url;

    /**
     * @param handler,url  Handler对象和请求url地址
     * */
    public HttpUtil(Handler handler, String url) {
        this.handler = handler;
        this.url = url;
    }

    // 获得Post请求对象request
    public static HttpPost getHttpPost(String url) {
        HttpPost request = new HttpPost(urlPrefix + url);
        return request;
    }

    // 根据请求获得响应对象response
    public static HttpResponse getHttpResponse(HttpPost request) throws Exception {
        HttpResponse response = new DefaultHttpClient().execute(request);
        return response;
    }

    /**
     * 新的现成处理网络数据传输任务
     * */
    public void run() {
        Log.i("info", urlPrefix + url);
        // 发送Post请求，获得响应查询结果
        Message msg = new Message();
        HttpPost request = HttpUtil.getHttpPost(urlPrefix + url);
        String result;//从服务器取得的JSON类型的数据
        try {
            // 获得响应对象
            HttpResponse response = HttpUtil.getHttpResponse(request);
            // 判断是否请求成功
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获得响应
                result = EntityUtils.toString(response.getEntity());
                msg.obj = result;//从服务器获得的结果
            } else {
                msg.obj = "load_error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.obj = "net_error";
        }
        handler.sendMessage(msg);
    }

}