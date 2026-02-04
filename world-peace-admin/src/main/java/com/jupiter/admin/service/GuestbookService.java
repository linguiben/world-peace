package com.jupiter.admin.service;

import com.jupiter.admin.entity.WpGuestbookMessage;
import com.jupiter.admin.mapper.GuestbookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuestbookService {

    private static final int IN_MEMORY_LIMIT = 50;

    private final GuestbookMapper guestbookMapper;

    private final ConcurrentLinkedDeque<WpGuestbookMessage> inMemoryMessages = new ConcurrentLinkedDeque<>();
    private final AtomicLong inMemoryId = new AtomicLong(0);
    private volatile boolean dbAvailable = true;

    public List<WpGuestbookMessage> listMessages(int limit) {
        int safeLimit = Math.max(1, Math.min(limit, IN_MEMORY_LIMIT));
        try {
            List<WpGuestbookMessage> messages = guestbookMapper.listMessages(safeLimit);
            dbAvailable = true;
            return messages != null ? messages : List.of();
        } catch (Exception e) {
            log.debug("DB guestbook unavailable, using in-memory: {}", e.getMessage());
            dbAvailable = false;
            return snapshotInMemory(safeLimit);
        }
    }

    public boolean addMessage(String username, String content, String ip) {
        try {
            int rows = guestbookMapper.insertMessage(username, content, ip);
            dbAvailable = true;
            return rows > 0;
        } catch (Exception e) {
            log.debug("DB guestbook insert failed, using in-memory: {}", e.getMessage());
            dbAvailable = false;
            addInMemory(username, content, ip);
            return true;
        }
    }

    public boolean isDatabaseAvailable() {
        return dbAvailable;
    }

    private void addInMemory(String username, String content, String ip) {
        WpGuestbookMessage message = new WpGuestbookMessage();
        message.setId(inMemoryId.incrementAndGet());
        message.setUsername(username);
        message.setContent(content);
        message.setIp(ip);
        message.setCreatedAt(LocalDateTime.now());

        inMemoryMessages.addFirst(message);
        while (inMemoryMessages.size() > IN_MEMORY_LIMIT) {
            inMemoryMessages.pollLast();
        }
    }

    private List<WpGuestbookMessage> snapshotInMemory(int limit) {
        List<WpGuestbookMessage> snapshot = new ArrayList<>(limit);
        int count = 0;
        for (WpGuestbookMessage message : inMemoryMessages) {
            snapshot.add(message);
            count += 1;
            if (count >= limit) {
                break;
            }
        }
        return snapshot;
    }
}
