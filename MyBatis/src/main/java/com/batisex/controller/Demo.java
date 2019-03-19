package com.batisex.controller;

import com.batisex.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/",description = "这是我的第一个demo")
@RequestMapping("v1")
public class Demo {

    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCounts",method = RequestMethod.GET)
    @ApiOperation(value = "这是获取到用户数",httpMethod = "GET")
    public int getUserCounts(){
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/setUser",method = RequestMethod.POST)
    @ApiOperation(value = "这是添加一个用户",httpMethod = "POST")
    public int addUser(@RequestBody User user){
        return template.insert("adduser",user);
    }
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    @ApiOperation(value = "这是修改用户",httpMethod = "POST")
    public int updataUser(@RequestBody User user){
        return template.update("updateuser",user);
    }
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ApiOperation(value = "这是删除用户",httpMethod = "POST")
    public int deleteUser(@RequestBody int id){
        return template.delete("deleteuser",id);
    }

}
