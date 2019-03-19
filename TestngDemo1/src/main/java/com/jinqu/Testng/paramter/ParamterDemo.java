package com.jinqu.Testng.paramter;



import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//使用xml文件进行参数化 param.xml文件
public class ParamterDemo {
    @Test
    @Parameters({"name","age"})
    public void param(String name,int age){
        System.out.println("name = "+name+";age="+age);
    }
}
