/**
 * @className UserMapper
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2023-12-31 20:49
 */
package com.jupiter.dao;

import com.jupiter.pojo.JbioUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2023-12-31 20:49
 */
//@Repository("userMapper")
@Mapper /*3.4.0支持。MyBatis-Spring-Boot-Starter默认会scan这个注解。*/
public interface UserMapper {
    JbioUser selectUserByName(String name);

    JbioUser checkUser(JbioUser user);

    JbioUser selectUserById(int id);

    ArrayList<JbioUser> selectAllUser();

    int addUser(JbioUser user);

    int updateUserById(JbioUser user);

    int deleteUserById(int id);
}
