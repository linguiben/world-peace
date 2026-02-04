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

    int insertVisitLog(@Param("ip") String ip,
                       @Param("path") String path,
                       @Param("method") String method,
                       @Param("userAgent") String userAgent,
                       @Param("browser") String browser,
                       @Param("referer") String referer,
                       @Param("acceptLanguage") String acceptLanguage);

    int insertUser(@Param("username") String username,
                   @Param("nickname") String nickname,
                   @Param("password") String password);

    boolean existsByUsername(@Param("username") String username);
}
