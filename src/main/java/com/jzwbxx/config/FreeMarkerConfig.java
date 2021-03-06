package com.jzwbxx.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.jzwbxx.directive.FlashMessageDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 配置 - Freemarker
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;
    @Autowired
    private FlashMessageDirective flashMessageDirective;

    @PostConstruct
    public void setSharedVariable() {
        try {
            configuration.setSharedVariable("flashMessage", flashMessageDirective);
            configuration.setSharedVariable("shiro", new ShiroTags());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}