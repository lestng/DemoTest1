package com.apidemo.proper;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyGetCookie {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void beforProper(){
        //获取setlication文件中的url地址
        bundle=ResourceBundle.getBundle("setlication", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public void testGetCookie() throws IOException {
        //用来返回结果
        String result;
        //获取uri地址
        String uri = bundle.getString("getcookie.uri");
        //声明get方法
        HttpGet get = new HttpGet(this.url + uri);
        DefaultHttpClient client = new DefaultHttpClient();
        // 用来执行get方法
        HttpResponse response = client.execute(get);
        //获取返回结果
        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);

        //获取cookie信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        System.out.println(client);
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookiename = " + name + ";cookievalue = " + value);
        }
    }
        @Test(dependsOnMethods = {"testGetCookie"})
        public void testGetWithCookies() throws IOException {
        //获取uri地址
        String uri = bundle.getString("setcookie.uri");
        //拼接url地址并进行get对象声明
        HttpGet get = new HttpGet(this.url+uri);
        //声明Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //设置cookie信息
        client.setCookieStore(this.store);
        //执行get方法
        HttpResponse response = client.execute(get);
        //获取响应状态码
        int statuscode = response.getStatusLine().getStatusCode();
        System.out.println("状态码："+statuscode);
        if(statuscode==200){
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(result);
        }

    }



}
