package com.apidemo.proper;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyPostCookie {
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

        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookiename = " + name + ";cookievalue = " + value);
        }
    }
    @Test(dependsOnMethods = {"testGetCookie"})
    public void postMethod() throws IOException {
        //获取uri地址
        String uri = bundle.getString("post.with.cookie");
        //拼接测试地址
        String testurl = this.url+uri;
        //声明Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明post方法
        HttpPost post = new HttpPost(testurl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","zhangsan");
        param.put("age","18");
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);
        //将返回的响应结果字符串转化为json对象
        JSONObject resultJson = new JSONObject(result);
        //获取到结果值
        String success = (String) resultJson.get("hde");
        String status = (String) resultJson.get("status");
        //具体判断返回结果的值
        Assert.assertEquals("chenggong",success);
        Assert.assertEquals("1",status);
    }

    @Test
    public void testLogIn() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("");
        //将获取的参数转变为json格式
        JSONObject param = new JSONObject();
        //设置参数
        param.put("name","");
        param.put("password","");
        //设置头信息
        post.setHeader("content-type","application/json");
        //将参数添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        String result;
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        //获取cookies信息
        this.store = client.getCookieStore();

        System.out.println(result);
        //将返回的响应结果字符串转化为json对象
        JSONObject resultJson = new JSONObject(result);
        //获取到结果值
        String success = (String) resultJson.get("hde");
        String status = (String) resultJson.get("status");

    }
}
