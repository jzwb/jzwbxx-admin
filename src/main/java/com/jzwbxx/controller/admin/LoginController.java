package com.jzwbxx.controller.admin;

import com.jzwbxx.shiro.AuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * controller - 后台登录
 */
@Controller("adminLoginController")
@RequestMapping("/admin")
public class LoginController extends BaseController {

    /**
     * 登录页
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/admin/login";
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password, RedirectAttributes redirectAttributes) {
        if (StringUtils.isAllEmpty(username) || StringUtils.isBlank(password)) {
            addFlashMessage(redirectAttributes, "登录失败,用户名或密码不允许为空");
            return "redirect:/admin/login/";
        }
        AuthenticationToken authenticationToken = new AuthenticationToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            if (e instanceof UnknownAccountException) {
                addFlashMessage(redirectAttributes, "登录失败,账号不存在");
            } else if (e instanceof IncorrectCredentialsException) {
                addFlashMessage(redirectAttributes, "登录失败,账号与密码不匹配");
            } else {
                addFlashMessage(redirectAttributes, "登录失败,未知异常");
            }
            return "redirect:/admin/login/";
        }
        return "redirect:/admin/main/";
    }
}
