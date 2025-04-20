package com.jupiter.mvc.controller;

import com.jupiter.mvc.dao.UserMapper;
import com.jupiter.mvc.pojo.JbioUser;
import com.jupiter.mvc.service.MapTest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
* @Description:
* @author: Jupiter.Lin
* @version: V1.0 
* @date: 2021年6月3日 下午3:31:40
*/
@RequestMapping("/jupiter")
@RestController
public class IndexController {

    @Value("${project.name}")
    private String projectName;

    // @ResponseBody //以jason格式返回
    @RequestMapping("/index")
    public String index() {
        return "success";
    }
    // @ResponseBody
    @RequestMapping("/listIndex")
    public List<String> listIndex() {
        List<String> list = new ArrayList<>();
        list.add("hellow");
        list.add("world");
        return list;
    }
    @RequestMapping("/demoError")
    public Integer demoError(HttpServletRequest request) {
        String id = request.getParameter("id");
        int id1 = Integer.parseInt(id);
        return id1;
    }


    @Autowired(required = false)
    private MapTest mapTest;

    @RequestMapping("/mapTest")
    public MapTest mapTest(){
        return this.mapTest;
    }

    @RequestMapping("/project")
    public String getProjectName(){
        return this.projectName;
    }

    /**
     * 1.引入依赖mybatis-spring-boot-starter
     * 2.配置文件application-dev.yml中的DataSource及url
     * 3.配置type-aliases-package（Mapper中用到的参数或返回的模型）和 mapper-locations
     * 4.使用@Mapper 或者 @MapperScan("<packageName>")
     * Spring将自动通过DataSource创建SqlSessionFactory -> SqlSessionTemplate
     * mybatis-spring-boot-autoconfigure:
     * https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html
     * mybatis-spring-boot-autoconfigure:
     * https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html
     * Scanning for mappers:
     * https://mybatis.org/spring/mappers.html#scan
     */
    @Autowired
    private SqlSession sqlSession;

    @RequestMapping("/listUser")
    public String getUserList(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<JbioUser> jbioUsers = userMapper.selectAllUser();
        sqlSession.commit();
        return jbioUsers.toString();
    }

}
