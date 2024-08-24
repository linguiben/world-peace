/**
 * @className Hello
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-21 14:52
 */
package com.jupiter.admin.controllor;

import com.jupiter.admin.config.AdminConfig;
import com.jupiter.admin.service.FileSaveService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-02-21 14:52
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class HelloController {

    @Value("${governmentIssuedNumber}")
    private String governmentIssuedNumber;

    // @Autowired
    private final FileSaveService fileSaveService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("governmentIssuedNumber",governmentIssuedNumber);
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request) throws IOException {
        log.info("hello ");
        return "Hello World!";
    }

    @RequestMapping("/hello/{name}")
    @ResponseBody
    public String helloName(@PathVariable String name){
        log.info("hello/{} ",name);
        return "Hello World! " + name;
    }

    @Value("${food.rice}")
    private String rice;
    @Value("${food.meat}")
    private String meat;

     //@Value("#{${wp-config.admin-config}}") //测试绑定yml中的配置的Map，但没有成功
     //private Map<String,String> adminConfigMap;

    @Autowired
    private AdminConfig adminConfig;

    @RequestMapping("/json")
    @ResponseBody
    public Food json(){
        Food food = new Food();
        food.setMeat(this.rice);
        food.setRice(this.meat);

        return food;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam("file") MultipartFile file, @RequestParam("desc") String desc) throws IOException {
        /*Spring3.1.2 升级到 5.1.12后，满足一定条件时无法再通过request获取到输入流，推荐通过MultipartFile来接收*/
//        ServletInputStream is = request.getInputStream();
//        System.out.println(is.available());
//        byte[] buffer = new byte[1024_000];
//        int read = is.read(buffer);
//        System.out.println(new String(buffer, 0, read));
//        String desc = request.getParameter("desc");

        return fileSaveService.save(file, desc);
        // return "redirect:/";
    }
}

@Data
class Food{
    private String meat;
    private String rice;
}
