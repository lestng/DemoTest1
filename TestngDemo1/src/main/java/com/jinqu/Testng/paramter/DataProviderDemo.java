package com.jinqu.Testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
//使用DataProvider进行参数化
public class DataProviderDemo {

    @Test(dataProvider = "data")
    public void dataProviderTest(String name,int age){
        System.out.println("name="+name+";age="+age);
    }
    @DataProvider(name = "data")
    public Object[][] dataTest(){
        Object[][] objects =new Object[][]{
                {"name",21}
        };
        return objects;
    }
    @Test(dataProvider = "methoddata")
    public void test1(String name,int age){
        System.out.println("test1的name="+name+";age="+age);
    }
    @Test(dataProvider = "methoddata")
    public void test2(String name,int age){
        System.out.println("test2的name="+name+";age="+age);
    }
    @DataProvider(name = "methoddata")
    public Object[][] methodData(Method method){
        Object[][] objects1 =null;
          if (method.getName().equals("test1")){
              objects1=new Object[][]{
                  {"zhangsan", 23},
                  {"lisi", 32}
              };
          }else if (method.getName().equals("test2"))
          {objects1 = new Object[][]{
                  {"shdisa",45}
          };
          }
        return objects1;
    }

}
