package com.jzwbxx.controller.admin;

import com.jzwbxx.common.Message;
import com.jzwbxx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller - 后台登录
 */
@Controller("adminLoginController")
@RequestMapping("/admin")
public class LoginController extends BaseController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        if (adminService.isLogin()) {
            return "redirect:/admin/main/";
        }
        return "/admin/login";
    }

    /**
     * 登陆状态
     * @return
     */
    @GetMapping("/login/status")
    @ResponseBody
    public Message loginStatus(){
        return Message.success("请求成功", adminService.isLogin());
    }
}
