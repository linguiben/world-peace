/**
 * @className VegetableConfig
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-22 13:37
 */
package com.jupiter.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *@desc 配置绑定Demo (复杂类)
 *@author Jupiter.Lin
 *@date 2024-02-22 13:37
 */
@Data
@Configuration // 声明为配置类
@ConfigurationProperties(prefix = "vegetables") // 需要配合配置绑定处理器spring-boot-configuration-processor
@PropertySource("classpath:vegetables.properties")
public class VegetableConfig {

    private String potato;
    private String eggplant;
    private String greenpepper;
}
