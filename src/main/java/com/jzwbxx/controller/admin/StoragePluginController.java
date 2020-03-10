package com.jzwbxx.controller.admin;

import com.jzwbxx.common.Message;
import com.jzwbxx.plugin.StoragePlugin;
import com.jzwbxx.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * controller - 存储插件
 */
@Controller("adminStoragePluginController")
@RequestMapping("/admin/storage_plugin")
public class StoragePluginController extends BaseController {

    @Autowired
    private PluginService pluginService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "/admin/storage_plugin/index";
    }

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Message list() {
        List<StoragePlugin> list = pluginService.getStoragePlugins();
        return Message.success("成功", list);
    }
}