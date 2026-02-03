package com.jupiter.admin.controllor;

import com.jupiter.admin.entity.WpUser;
import com.jupiter.admin.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    public static final String SESSION_USER_KEY = "currentUser";

    // simple RFC-ish email regex (good for validation without heavy deps)
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String username,
                                      @RequestParam String password,
                                      HttpSession session) {
        Map<String, Object> result = new HashMap<>();

        if (!loginService.isDatabaseAvailable()) {
            result.put("success", false);
            result.put("message", "登录服务暂时不可用，请稍后再试");
            return result;
        }

        Optional<WpUser> userOpt = loginService.login(username, password);

        if (userOpt.isPresent()) {
            WpUser user = userOpt.get();
            session.setAttribute(SESSION_USER_KEY, user);
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("username", user.getUsername());
            log.info("User logged in: {}", username);
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }

        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        WpUser user = (WpUser) session.getAttribute(SESSION_USER_KEY);
        if (user != null) {
            log.info("User logged out: {}", user.getUsername());
        }
        session.removeAttribute(SESSION_USER_KEY);
        return "redirect:/";
    }

    @GetMapping("/login/status")
    @ResponseBody
    public Map<String, Object> loginStatus(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        WpUser user = (WpUser) session.getAttribute(SESSION_USER_KEY);

        result.put("loggedIn", user != null);
        result.put("dbAvailable", loginService.isDatabaseAvailable());

        if (user != null) {
            result.put("username", user.getUsername());
        }

        return result;
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(@RequestParam String username,
                                         @RequestParam String password) {
        Map<String, Object> result = new HashMap<>();

        if (!loginService.isDatabaseAvailable()) {
            result.put("success", false);
            result.put("message", "注册服务暂时不可用，请稍后再试");
            return result;
        }

        if (username == null || username.trim().length() < 3) {
            result.put("success", false);
            result.put("message", "用户名至少需要3个字符");
            return result;
        }

        // Enforce email format for usernames
        String trimmed = username.trim();
        if (!EMAIL_PATTERN.matcher(trimmed).matches()) {
            result.put("success", false);
            result.put("message", "用户名必须为有效的邮箱地址");
            return result;
        }

        if (password == null || password.length() < 6) {
            result.put("success", false);
            result.put("message", "密码至少需要6个字符");
            return result;
        }

        if (loginService.existsByUsername(trimmed)) {
            result.put("success", false);
            result.put("message", "用户名已存在");
            return result;
        }

        boolean success = loginService.register(trimmed, password);

        if (success) {
            result.put("success", true);
            result.put("message", "注册成功，请登录");
        } else {
            result.put("success", false);
            result.put("message", "注册失败，请稍后再试");
        }

        return result;
    }
}
