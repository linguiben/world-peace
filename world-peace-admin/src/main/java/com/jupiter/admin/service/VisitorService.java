package com.jupiter.admin.service;

import com.jupiter.admin.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitorService {

    private final LoginMapper loginMapper;

    private final AtomicLong inMemoryVisitorCount = new AtomicLong(0);
    private volatile boolean dbAvailable = true;

    public long incrementAndGetVisitorCount(String ip,
                                           String path,
                                           String method,
                                           String userAgent,
                                           String referer,
                                           String acceptLanguage) {
        long memoryCount = inMemoryVisitorCount.incrementAndGet();

        try {
            // 1) record aggregate counter
            loginMapper.incrementVisitorCount();

            // 2) record detailed visit log
            loginMapper.insertVisitLog(
                    ip,
                    path,
                    method,
                    userAgent,
                    detectBrowser(userAgent),
                    referer,
                    acceptLanguage
            );

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

    /**
     * Lightweight UA classification.
     * Not meant to be perfect; keeps a stable, useful browser label for quick analysis.
     */
    static String detectBrowser(String userAgent) {
        if (userAgent == null || userAgent.isBlank()) {
            return "Unknown";
        }

        String ua = userAgent.toLowerCase(Locale.ROOT);

        if (ua.contains("edg/")) return "Edge";
        if (ua.contains("opr/") || ua.contains("opera")) return "Opera";
        if (ua.contains("chrome/") && !ua.contains("edg/") && !ua.contains("opr/")) return "Chrome";
        if (ua.contains("safari/") && !ua.contains("chrome/")) return "Safari";
        if (ua.contains("firefox/")) return "Firefox";
        if (ua.contains("msie") || ua.contains("trident/")) return "IE";

        if (ua.contains("curl/") || ua.contains("wget") || ua.contains("httpclient")) return "CLI";

        return "Other";
    }
}
