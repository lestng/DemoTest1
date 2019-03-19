package com.apidemo.cases;

import com.apidemo.config.TestConfig;
import com.apidemo.model.InterfaceName;
import com.apidemo.model.LoginCase;
import com.apidemo.utils.ConfigFile;
import com.apidemo.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = "loginTrue",description = "测试的准备工作,获取url地址")
    public void beforeTest(){
        TestConfig.getuserinfoUrl= ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.adduserUrl=ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.getuserlistURL=ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateuserinfoURL=ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.loginURL=ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.defaultHttpClient = new DefaultHttpClient();
    }
    @Test(groups = "loginTrue",description = "登录成功的接口测试")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginURL);
        //发送请求
        String result = getResult(loginCase);
        //验证结果

        Assert.assertEquals(loginCase.getExpected(),result);
    }



    @Test(groups = "loginFalse",description = "登录失败的接口测试")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginURL);

        //发送请求
        String result = getResult(loginCase);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(),result);

    }
    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post =new HttpPost(TestConfig.loginURL);
        //将获取的参数转变为json格式
        JSONObject param = new JSONObject();
        //传参数进入json中
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        //设置头信息
        post.setHeader("content-type","application/json");
        //将参数添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        String result;//存放返回结果的
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        //获取cookies信息
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
       // System.out.println(result);

        return result;
    }
}
