package com.jinqu.Testng;

import org.testng.annotations.Test;

public class DepandDemo {
    @Test
    public void test1(){
        System.out.println("test1运行成功");
        throw new RuntimeException();
    }
    @Test(dependsOnMethods = "test1")
    public void test2(){
        System.out.println("test2运行成功");
    }

}
