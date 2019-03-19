package com.jinqu.Testng;

import org.testng.annotations.*;

public class Demo1 {
    @Test(enabled = true)
    public void testDemo(){
        System.out.println("测试用例一");
    }
    @Test(enabled = false)
    //当enabled值为false时不执行
    public void test1Demo(){
        System.out.println("测试用例222222");
    }
    @BeforeMethod
    public void beforeMethodDemo(){
        System.out.println("用例执行前运行");
    }
    @AfterMethod
    public void afterMethodDemo(){
        System.out.println("用例执行后运行");
    }
    @BeforeClass
    public void beforeClassDemo(){
        System.out.println("类运行之前运行");
    }
    @AfterClass
    public void afterClassDemo(){
        System.out.println("类运行之后运行");
    }
}
