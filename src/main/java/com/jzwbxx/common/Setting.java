package com.jzwbxx.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * 配置
 */
@Configuration
@Component
public class Setting {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setLocations(new ClassPathResource("setting.properties"));
        pspc.setIgnoreUnresolvablePlaceholders(true);//禁止表达式替换
        return pspc;
    }

    private static final String SEPARATOR = ",";//分隔符

    @Value("${siteUrl}")
    private String siteUrl;//网站网址
    @Value("${maxInMemorySize}")
    private Integer maxInMemorySize;//最大内存缓存(单位:M)
    @Value("${maxUploadSize}")
    private Integer maxUploadSize;//最大上传大小(单位:M)
    @Value("${uploadImageMaxSize}")
    private Integer uploadImageMaxSize;//上传图片大小限制(单位:M)
    @Value("${uploadImagePath}")
    private String uploadImagePath;//上传图片路径
    @Value("${uploadImageExtension}")
    private String uploadImageExtension;//上传图片扩展名

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public Integer getMaxInMemorySize() {
        return maxInMemorySize;
    }

    public void setMaxInMemorySize(Integer maxInMemorySize) {
        this.maxInMemorySize = maxInMemorySize;
    }

    public Integer getMaxUploadSize() {
        return maxUploadSize;
    }

    public void setMaxUploadSize(Integer maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    public Integer getUploadImageMaxSize() {
        return uploadImageMaxSize;
    }

    public void setUploadImageMaxSize(Integer uploadImageMaxSize) {
        this.uploadImageMaxSize = uploadImageMaxSize;
    }

    public String getUploadImagePath() {
        return uploadImagePath;
    }

    public void setUploadImagePath(String uploadImagePath) {
        this.uploadImagePath = uploadImagePath;
    }

    public String getUploadImageExtension() {
        return uploadImageExtension;
    }

    public void setUploadImageExtension(String uploadImageExtension) {
        this.uploadImageExtension = uploadImageExtension;
    }

    public String[] getUploadImageExtensions() {
        return StringUtils.split(uploadImageExtension, SEPARATOR);
    }
}