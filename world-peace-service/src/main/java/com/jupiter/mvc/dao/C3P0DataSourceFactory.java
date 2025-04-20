/**
 * 
 */
package com.jupiter.mvc.dao;

import com.jupiter.mvc.pojo.JbioUser;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @author: Jupiter
 * @date @time
 */
@Slf4j
public class C3P0DataSourceFactory extends UnpooledDataSourceFactory {
    
    public C3P0DataSourceFactory() {
        this.dataSource = new ComboPooledDataSource();
    }

    /**
     * 这段代码展示了如何使用 MyBatis 框架进行数据库操作。具体步骤如下：
     * 1) 初始化 MyBatis 环境：读取配置文件 mybatis-config.xml，创建 SqlSessionFactory 和 SqlSession。
     * 2) 获取 Mapper 接口实例：通过 SqlSession 获取 UserMapper 接口的代理类实例。
     * 3) 执行 SQL 查询：调用 UserMapper 的 selectUserById 方法查询用户信息并打印结果。
     * 4) 提交和关闭会话：自动提交事务并关闭 SqlSession。
     *
     * create schema dbo;
     * create table dbo.Jobio_User(id int, userName varchar(100), password varchar(100), optionTime timestamp, remark varchar(100), version decimal(5,2));
     * insert into dbo.Jobio_User(id,userName,password) values(1,'Jupiter','Jupiter');
     * select * from dbo.Jobio_User;
     */
    public static void main(String[] args) throws IOException {

        // SqlSessionFactory -> sqlSession -> mapper(代理类) -> 执行sql

        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);

        // 1.获取SqlSessionFactory 和 sqlSession
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession(true); // auto commit
        log.info("sqlSessionFactory:{} ", sqlSessionFactory);

        // 2.将mapper接口注册到mybatis的sqlSessionFactory
        // mybatis-config.xml中配置了<mapper resource="mappers/UserMapper.xml" /> 后，以下不需要，否则会出现is already known to the MapperRegistry
        // sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);

        //2.使用mapper执行SQL。此举需要在"mybatis-config.xml"配置
        //检查此userMapper是否上面注册的userMapper，猜测这里获取到的是代理类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        JbioUser user = userMapper.selectUserById(1);
        log.info("user: {}", user);

        sqlSession.commit();
        sqlSession.close();
    }
}
