package com.jupiter.mvc.service;

import com.jupiter.mvc.dao.UserMapper;
import com.jupiter.mvc.pojo.JbioUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/1/25
 */
@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void createUser() {
        System.out.println("create user");
    }

    public void updateUser() {
        System.out.println("update user");
    }

    public void deleteUser() {
        System.out.println("delete user");
    }

    public JbioUser getUser(Integer id) {
        return userMapper.selectUserById(id);
    }
}
