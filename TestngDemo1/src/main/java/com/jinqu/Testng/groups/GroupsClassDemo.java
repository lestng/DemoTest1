package com.jinqu.Testng.groups;

import org.testng.annotations.Test;
//测试组的类分组运行
@Test(groups = "two")
public class GroupsClassDemo {
    @Test
    public void groupsClassDemo(){
        System.out.println("two标签组运行");
    }
}
