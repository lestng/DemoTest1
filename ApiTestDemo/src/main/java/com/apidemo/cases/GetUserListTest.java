package com.apidemo.cases;

import com.apidemo.config.TestConfig;
import com.apidemo.model.GetUserListCase;
import com.apidemo.model.User;
import com.apidemo.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserListTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取性别为男的用户信息接口测试")
    public void getUserList() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getuserlistURL);
        //发送请求 获取结果
        JSONArray resultJson = getJsonResult(getUserListCase);
        //验证
        List<User> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);
        for (User u:userList){
            System.out.println(u.toString());
        }
        //
        JSONArray userListJson = new JSONArray(userList);
        Assert.assertEquals(userListJson.length(),resultJson.length());
        for(int i = 0;i<resultJson.length();i++){
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }
    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getuserlistURL);
        JSONObject param = new JSONObject();

        param.put("userName",getUserListCase.getUserName());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());
        //设置请求头
        post.setHeader("content-type","application/json");
        //设置参数
        StringEntity entity = new StringEntity(param.toString());
        post.setEntity(entity);
        //设置cookies信息
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray = new JSONArray(result);

        return jsonArray;
    }
}
