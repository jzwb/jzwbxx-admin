package com.jzwbxx.config;

import com.jzwbxx.interceptor.CommonInterceptor;
import com.jzwbxx.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置 - 拦截器、视图
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CommonInterceptor commonInterceptor;
    @Autowired
    private LogInterceptor logInterceptor;

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor).addPathPatterns("/admin/**");
        registry.addInterceptor(logInterceptor).addPathPatterns("/admin/**");
    }

    /**
     * 视图
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //后台
        registry.addViewController("/admin/index/").setViewName("/admin/index");//首页
        registry.addViewController("/admin/main/").setViewName("/admin/main");//页面框架
    }
}