package com.jupiter.stock;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
//@Component
@ConfigurationProperties(prefix = "stock") // 开启配置绑定，绑定配置文件中的值
public class MarketInfo {

    @Value("${stock.market}") // 加了@ConfigurationProperties后，可省
    private String market;

    // @Data 只会生成无参构造器
    public MarketInfo() {
    }

    // 写了有参构造器后，必须手动写无参构造器
    public MarketInfo(String market) {
        this.market = market;
    }
}

