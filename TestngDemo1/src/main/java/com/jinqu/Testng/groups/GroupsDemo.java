package com.jinqu.Testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

@Test(groups = "one")
public class GroupsDemo {
    //方法分组测试
    @Test(groups = "server")
    public void groups1(){
        System.out.println("server组111");
    }
    @Test(groups = "server")
    public void groups2(){
        System.out.println("server组2222");
    }
    @Test(groups = "client")
    public void groupsClient1(){
        System.out.println("client组111");
    }
    @Test(groups = "client")
    public void groupscClient2(){
        System.out.println("client组2222");
    }
    @BeforeGroups("server")
    public void beforGroup(){
        System.out.println("server运行之前运行");
    }
    @AfterGroups("server")
    public void afterGroup(){
        System.out.println("server运行之后运行");
    }
    @BeforeGroups("client")
    public void beforGroup1(){
        System.out.println("client运行之前运行");
    }
    @AfterGroups("client")
    public void afterGroup1(){
        System.out.println("client运行之后运行");
    }
}
