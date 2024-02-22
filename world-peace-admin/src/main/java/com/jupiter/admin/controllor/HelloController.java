/**
 * @className Hello
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-21 14:52
 */
package com.jupiter.admin.controllor;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-21 14:52
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }

    @Value("${food.rice}")
    private String rice;
    @Value("${food.meat}")
    private String meat;

    @RequestMapping("/json")
    @ResponseBody
    public Food json(){
        Food food = new Food();
        food.setMeat(this.rice);
        food.setRice(this.meat);
        return food;
    }

}

@Data
class Food{
    private String meat;
    private String rice;
}
