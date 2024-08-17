/**
 * @className AdminProperty
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-08-17 16:32
 */
package com.jupiter.admin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Jupiter.Lin
 * @desc 配置绑定，绑定yml中定义的Map
 * @date 2024-08-17 16:32
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wp-config")
public class AdminConfig {

    // 绑定yaml中的wp-config.admin-config
    private Map<String, String> adminConfig;

}
