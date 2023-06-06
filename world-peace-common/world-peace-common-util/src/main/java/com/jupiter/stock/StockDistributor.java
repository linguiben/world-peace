package com.jupiter.stock;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

// 测试自动绑定
@Data
@Component
@ConfigurationProperties(prefix = "stock")
public class StockDistributor {

    private String market;

    private Map<String,String> stockPriceMap;
}
