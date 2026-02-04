/**
 * @className WpUser
 * @desc User entity for login functionality
 * @author Jupiter.Lin
 * @date 2026-01-28
 */
package com.jupiter.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WpUser {

    private Long id;
    private String username;
    private String nickname;
    private String password;
    private Date createdAt;
    private Date lastLoginAt;
    private Integer loginCount;

    public WpUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
