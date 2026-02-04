package com.jupiter.admin.mapper;

import com.jupiter.admin.entity.WpGuestbookMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GuestbookMapper {

    List<WpGuestbookMessage> listMessages(@Param("limit") int limit);

    int insertMessage(@Param("username") String username,
                      @Param("content") String content,
                      @Param("ip") String ip);
}
