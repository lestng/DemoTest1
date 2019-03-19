package com.apidemo.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Reader;

public class DatabaseUtil {

    public static SqlSession getSqlSession() throws IOException {
        //获取配置源文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        //build出配置源文件
        SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(reader);
        //sqlSession就是能执行配置文件中的sql语句
        SqlSession sqlSession = factory.openSession();
        return  sqlSession;
    }
}
