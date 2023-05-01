package com.jupiter.calc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Data
@Component
//@Configuration //这个我这里虽然存在时能成功,不过我注释了也是可以的,这个是看网上有人写就跟着写上的
//@PropertySource(value = {"classpath:/application.yml"}, encoding = "utf-8") //有的人是写了这个注解能成功,但是我这边不能有这个注解,有的话,就连编译都会报错
@ConfigurationProperties(prefix = "stockPrice")
public class MapTest {

//    @Autowired
    private Map<String, String> maps;

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }
}
