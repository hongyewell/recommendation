package com.ahau.app.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	public static SqlSession getSqlSession() {  
	      
        try {  
            //InputStream inputStream = MybatisUtil.class.getClassLoader().getResourceAsStream("conf.xml");  
            InputStream inputStream = Resources.getResourceAsStream("conf.xml");  
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);  
              
            SqlSession session = factory.openSession();  
              
            return session;  
              
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return null;  
    }  
	
}
