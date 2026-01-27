package com.jupiter.admin.service;

import com.jupiter.admin.entity.WpUser;
import com.jupiter.admin.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginMapper loginMapper;

    private volatile boolean dbAvailable = true;

    public Optional<WpUser> login(String username, String password) {
        try {
            WpUser user = loginMapper.checkUser(username, password);
            if (user != null) {
                loginMapper.updateLoginInfo(user.getId());
                dbAvailable = true;
            }
            return Optional.ofNullable(user);
        } catch (Exception e) {
            log.warn("Database unavailable for login: {}", e.getMessage());
            dbAvailable = false;
            return Optional.empty();
        }
    }

    public Optional<WpUser> findByUsername(String username) {
        try {
            dbAvailable = true;
            return Optional.ofNullable(loginMapper.findByUsername(username));
        } catch (Exception e) {
            log.warn("Database unavailable: {}", e.getMessage());
            dbAvailable = false;
            return Optional.empty();
        }
    }

    public boolean isDatabaseAvailable() {
        return dbAvailable;
    }

    public long getTotalLoginCount() {
        try {
            Long count = loginMapper.getTotalLoginCount();
            dbAvailable = true;
            return count != null ? count : 0L;
        } catch (Exception e) {
            log.warn("Cannot get login count: {}", e.getMessage());
            dbAvailable = false;
            return 0L;
        }
    }

    public boolean existsByUsername(String username) {
        try {
            boolean exists = loginMapper.existsByUsername(username);
            dbAvailable = true;
            return exists;
        } catch (Exception e) {
            log.warn("Cannot check username existence: {}", e.getMessage());
            dbAvailable = false;
            return false;
        }
    }

    public boolean register(String username, String password) {
        try {
            if (loginMapper.existsByUsername(username)) {
                return false;
            }
            int rows = loginMapper.insertUser(username, password);
            dbAvailable = true;
            log.info("New user registered: {}", username);
            return rows > 0;
        } catch (Exception e) {
            log.warn("Registration failed: {}", e.getMessage());
            dbAvailable = false;
            return false;
        }
    }
}
