package com.jzwbxx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 配置 - 文件上传
 */
@Configuration
public class MultipartConfig {

    @Value("${config.multipart.maxInMemorySize}")
    private int maxInMemorySize;
    @Value("${config.multipart.maxUploadSize}")
    private int maxUploadSize;

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxInMemorySize(maxInMemorySize * 1024 * 1024);
        multipartResolver.setMaxUploadSize(maxUploadSize * 1024 * 1024);
        return multipartResolver;
    }
}