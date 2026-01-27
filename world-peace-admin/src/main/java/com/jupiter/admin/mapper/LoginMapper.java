package com.jupiter.admin.mapper;

import com.jupiter.admin.entity.WpUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {

    WpUser findByUsername(@Param("username") String username);

    WpUser checkUser(@Param("username") String username, @Param("password") String password);

    int updateLoginInfo(@Param("id") Long id);

    Long getTotalLoginCount();

    int incrementVisitorCount();

    Long getVisitorCount();

    int insertUser(@Param("username") String username, @Param("password") String password);

    boolean existsByUsername(@Param("username") String username);
}
