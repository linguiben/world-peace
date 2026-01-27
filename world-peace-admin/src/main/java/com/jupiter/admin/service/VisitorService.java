package com.jupiter.admin.service;

import com.jupiter.admin.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitorService {

    private final LoginMapper loginMapper;

    private final AtomicLong inMemoryVisitorCount = new AtomicLong(0);
    private volatile boolean dbAvailable = true;

    public long incrementAndGetVisitorCount() {
        long memoryCount = inMemoryVisitorCount.incrementAndGet();

        try {
            loginMapper.incrementVisitorCount();
            Long dbCount = loginMapper.getVisitorCount();
            dbAvailable = true;
            return dbCount != null ? dbCount : memoryCount;
        } catch (Exception e) {
            log.debug("DB visitor tracking unavailable, using in-memory: {}", e.getMessage());
            dbAvailable = false;
            return memoryCount;
        }
    }

    public long getVisitorCount() {
        try {
            Long dbCount = loginMapper.getVisitorCount();
            dbAvailable = true;
            if (dbCount != null && dbCount > 0) {
                return dbCount;
            }
        } catch (Exception e) {
            log.debug("DB visitor count unavailable: {}", e.getMessage());
            dbAvailable = false;
        }
        return inMemoryVisitorCount.get();
    }

    public boolean isDatabaseAvailable() {
        return dbAvailable;
    }
}
