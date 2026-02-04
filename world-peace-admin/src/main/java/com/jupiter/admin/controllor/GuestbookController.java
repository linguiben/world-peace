package com.jupiter.admin.controllor;

import com.jupiter.admin.entity.WpUser;
import com.jupiter.admin.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GuestbookController {

    private static final int MESSAGE_LIMIT = 50;
    private static final int MAX_CONTENT_LENGTH = 500;
    private final GuestbookService guestbookService;

    @GetMapping("/guestbook")
    public String guestbook(Model model, HttpSession session) {
        WpUser currentUser = (WpUser) session.getAttribute(LoginController.SESSION_USER_KEY);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("loggedIn", currentUser != null);
        model.addAttribute("messages", guestbookService.listMessages(MESSAGE_LIMIT));
        model.addAttribute("dbAvailable", guestbookService.isDatabaseAvailable());
        return "guestbook";
    }

    @PostMapping("/guestbook/message")
    public String addMessage(@RequestParam("content") String content,
                             HttpSession session,
                             HttpServletRequest request,
                             RedirectAttributes redirectAttributes) {
        WpUser currentUser = (WpUser) session.getAttribute(LoginController.SESSION_USER_KEY);
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "请先登录后再留言");
            return "redirect:/guestbook";
        }

        String trimmedContent = content == null ? "" : content.trim();
        if (trimmedContent.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "留言内容不能为空");
            return "redirect:/guestbook";
        }
        if (trimmedContent.length() > MAX_CONTENT_LENGTH) {
            redirectAttributes.addFlashAttribute("error", "留言内容不能超过" + MAX_CONTENT_LENGTH + "字");
            return "redirect:/guestbook";
        }

        String author = resolveAuthor(currentUser);

        String ip = getClientIp(request);
        boolean saved = guestbookService.addMessage(author, trimmedContent, ip);
        if (saved) {
            redirectAttributes.addFlashAttribute("success", "留言已发布");
        } else {
            redirectAttributes.addFlashAttribute("error", "留言失败，请稍后再试");
        }
        return "redirect:/guestbook";
    }

    private static String resolveAuthor(WpUser user) {
        if (user == null) {
            return "";
        }
        String nickname = user.getNickname();
        if (nickname != null && !nickname.isBlank()) {
            return nickname.trim();
        }
        String username = user.getUsername();
        return username == null ? "" : username.trim();
    }

    private static String getClientIp(HttpServletRequest request) {
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null && !xff.isBlank()) {
            String first = xff.split(",")[0].trim();
            if (!first.isEmpty()) return first;
        }

        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isBlank()) {
            return xRealIp.trim();
        }

        return request.getRemoteAddr();
    }
}
