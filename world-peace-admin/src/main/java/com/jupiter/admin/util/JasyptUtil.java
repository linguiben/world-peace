/**
 * @className JasyptUtil
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-22 15:10
 */
package com.jupiter.admin.util;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-22 15:10
 */
@Slf4j
@Component
public class JasyptUtil {
    @Value("${jasypt.encryptor.password}")
    private String salt;
    public void encryDemo(){
        System.out.println(this.salt);
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(this.salt); // 加盐
        String username = encryptor.encrypt("username");
        String password = encryptor.encrypt("abcd1234");

        // 每次加密后的结果都不一样
        log.warn("username:{},password:{}",username,password);

        System.out.println(encryptor.decrypt(username));
        System.out.println(encryptor.decrypt(password));
    }

//    @Bean
//    public BasicTextEncryptor encryptor(){
//        BasicTextEncryptor encryptor = new BasicTextEncryptor();
//        encryptor.setPassword(this.salt); // 加盐
//        return encryptor;
//    }

}
