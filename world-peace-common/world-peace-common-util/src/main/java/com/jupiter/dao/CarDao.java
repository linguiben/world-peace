package com.jupiter.dao;

import com.jupiter.domain.Car;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class CarDao {

    public void save(Car car) throws IOException {
        //原来使用jdbc存储
        //使用mybatis存储

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        InputStream is = Thread.currentThread().getContextClassLoader()
//                .getResourceAsStream("mybatis-config.xml");
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = builder.build(is) ;
        SqlSession session = factory.openSession(true) ;
        session.insert("save",car);
        //session.commit(true);
    }

    public void delete(Long cno ) throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = builder.build(is) ;
        SqlSession session = factory.openSession(true) ;
        session.delete("car.remove",cno);
    }
}
