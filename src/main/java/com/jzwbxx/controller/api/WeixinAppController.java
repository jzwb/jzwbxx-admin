package com.jzwbxx.controller.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzwbxx.common.Message;
import com.jzwbxx.config.WxMaConfig;
import com.jzwbxx.exception.ServiceException;
import com.jzwbxx.model.User;
import com.jzwbxx.service.UserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * controller - 微信小程序
*/
@Controller("apiWeixinAppController")
@RequestMapping("/api/weixin/app")
public class WeixinAppController {

    static final Logger LOGGER = LoggerFactory.getLogger(WeixinAppController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param appId
     * @param code
     * @param encryptedData
     * @param iv
     * @param request
     * @param response
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Message login(String appId, String code, String encryptedData, String iv, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            if (StringUtils.isBlank(appId) || StringUtils.isBlank(code) || StringUtils.isBlank(encryptedData) || StringUtils.isBlank(iv)) {
                return Message.error("参数错误");
            }
            WxMaJscode2SessionResult wxMaJscode2SessionResult = WxMaConfig.getMaService(appId).jsCode2SessionInfo(code);
            String sessionKey = wxMaJscode2SessionResult.getSessionKey();
            if (StringUtils.isBlank(sessionKey)) {
                return Message.error("获取微信账号信息异常");
            }
            String result = WxMaCryptUtils.decrypt(wxMaJscode2SessionResult.getSessionKey(), encryptedData, iv);
            JSONObject jsonObject = JSON.parseObject(result);
            String unionId = null;
            String openId = null;
            if (jsonObject.containsKey("unionId")) {
                unionId = jsonObject.get("unionId") != null ? jsonObject.get("unionId").toString() : null;
            }
            if (jsonObject.containsKey("openId")) {
                openId = jsonObject.get("openId") != null ? jsonObject.get("openId").toString() : null;
            }
            if (StringUtils.isBlank(unionId) || StringUtils.isBlank(openId)) {
                return Message.error("获取微信账号信息异常");
            }
            User user = userService.findByUnionId(unionId);
            if (user != null) {
                //登录
                userService.login(user, false, null, request, response, session);
            } else {
                //注册
                user = userService.genRegisterUser(null, null, null, request, response);
                user.setUnionId(unionId);
                user.setOpenId(openId);
                userService.register(user, request, response, session);
            }
            return Message.success("登录成功");
        } catch (Exception e) {
            if (e instanceof WxErrorException) {
                LOGGER.error("微信登录凭证换取异常,code:{}", code, e);
            } else if (e instanceof ServiceException) {
                LOGGER.error("业务异常", e);
            } else {
                LOGGER.error("未知异常,code:{}", code, e);
            }
            return Message.error("登陆失败");
        }
    }
}