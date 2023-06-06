package com.jupiter.config;

import com.jupiter.stock.MarketInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.io.InputStream;

//@Import(MarketInfo.class) // 往容器中注入bean
@ImportResource("classpath:beans.xml")  // 配置文件中的bean注入容器
@ConditionalOnMissingBean(name = "RT_SZ") // 不存在该bean时才执行
@Configuration(proxyBeanMethods = false) // default:true(bean单例)
@EnableConfigurationProperties(MarketInfo.class) // 属性绑定并且注册到容器, id为prefix-全类名
public class MyConfig {

    @Bean(name = "marketInfo2")
    public MarketInfo marketInfo(){
        return new MarketInfo();
    }

    @Bean/*(name = "sqlSession")*/
    public SqlSession sqlSession() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = builder.build(is);
        SqlSession sqlSession = factory.openSession(true);
        return sqlSession;
        // session.insert("save",car);
        //session.commit(true);
    }
}
