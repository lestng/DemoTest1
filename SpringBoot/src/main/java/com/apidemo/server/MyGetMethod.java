package com.apidemo.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getcookie1",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest  装请求信息类
        //HttpServerletResponse  装响应信息的类
        Cookie cookie = new Cookie("thone","123412");
        //用于返回cookie信息
        response.addCookie(cookie);
        return "cookie信息返回成功";
    }

    /**
     * 要求客户端携带cookie信息访问
     * 这是一个需要携带cookies信息的get请求
     */
    @RequestMapping(value = "/getdemo",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookie信息访问",httpMethod = "GET")
    public String getWithCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "需要携带cookie信息";
        }
        for(Cookie cookie :cookies){
            if(cookie.getName().equals("thone")&&
                    cookie.getValue().equals("123412")){
                return "模拟一个不带参数带cookies的get请求";
            }
        }
        return "需要携带cookie信息";
    }
    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url ： key=value&key=value
     */
    @RequestMapping(value = "/getque",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("xie",40);
        myList.put("easda",2);
        return myList;
    }
    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第二种实现方式 url ： ip:port/uri/start/end
     */
    @RequestMapping(value = "/getque/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "第二种需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> myGetList(@PathVariable Integer start,
                                       @PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("xie",40);
        myList.put("easda",2);
        return myList;
    }
}
