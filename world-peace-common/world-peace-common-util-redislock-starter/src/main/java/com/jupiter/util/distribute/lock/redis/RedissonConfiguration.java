package com.jupiter.util.distribute.lock.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @desc: The class RedissonConfiguration
 * @author: Jupiter.Lin
 * @date: 2024-09-10
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "redis.distribution.lock", name = "enabled", havingValue = "true")
@ComponentScan(
        basePackages="com.jupiter.util.distribute.lock.redis",
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.CUSTOM,
                classes = {TypeExcludeFilter.class}
        ), @ComponentScan.Filter(
                type = FilterType.CUSTOM,
                classes = {AutoConfigurationExcludeFilter.class}
        )}
)
public class RedissonConfiguration {

    // 此处更换自己的 Redis 地址即可
    @Value("${spring.redis.password:}")
    private String password;

    @Bean
    public RedissonClient redisson(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") String port) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(String.format("%s%s", "redis://", host + ":" + port))
                .setPassword(password) // 如果没有密码可以不设置
                .setConnectionPoolSize(64)              // 连接池大小
                .setConnectionMinimumIdleSize(8)        // 保持最小连接数
                .setConnectTimeout(1500)                // 建立连接超时时间
                .setTimeout(2000)                       // 执行命令的超时时间, 从命令发送成功时开始计时
                .setRetryAttempts(2)                    // 命令执行失败重试次数
                .setRetryInterval(1000);                // 命令重试发送时间间隔
        log.info("RedissonClient init success to: {}", host + ":" + port);
        return Redisson.create(config);
    }
}
