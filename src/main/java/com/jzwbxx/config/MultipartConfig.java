package com.jzwbxx.config;

import com.jzwbxx.common.Setting;
import com.jzwbxx.util.SettingUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 配置 - 文件上传
 */
@Configuration
public class MultipartConfig {

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        Setting setting = SettingUtils.get();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxInMemorySize(setting.getMaxInMemorySize() * 1024 * 1024);
        multipartResolver.setMaxUploadSize(setting.getMaxUploadSize() * 1024 * 1024);
        return multipartResolver;
    }
}