/**
 * @className JsonController
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-22 13:47
 */
package com.jupiter.admin.controllor;

import com.jupiter.admin.bean.Vegetables;
import com.jupiter.admin.config.VegetableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-22 13:47
 */
@RestController
public class JsonController {

    @Autowired
    private VegetableConfig vegetableConfig;

    @RequestMapping("/vegetables")
    public Vegetables vegetables(){
        Vegetables vegetables = new Vegetables();
        vegetables.setPotato(vegetableConfig.getPotato());
        vegetables.setEggplant(vegetableConfig.getEggplant());
        vegetables.setGreenpepper(vegetableConfig.getGreenpepper());

        return vegetables;
    }

    // decrypt
    @Value("${info.username}")
    private String username;
    @Value("${info.password}")
    private String password;

    @RequestMapping("/jasypt")
    public String jaspyt(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(username);
        stringBuffer.append("\t");
        stringBuffer.append(password);
        return stringBuffer.toString();
    }
}
