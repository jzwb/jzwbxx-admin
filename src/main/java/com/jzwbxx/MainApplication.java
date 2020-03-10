package com.jzwbxx;

import com.jzwbxx.interceptor.CommonInterceptor;
import com.jzwbxx.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@SpringBootApplication
@Configuration
public class MainApplication extends WebMvcConfigurerAdapter {

	@Autowired
	private CommonInterceptor commonInterceptor;
	@Autowired
	private LogInterceptor logInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor).addPathPatterns("/admin/**");
		registry.addInterceptor(logInterceptor).addPathPatterns("/admin/**");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//后台
		registry.addViewController("/admin/index/").setViewName("/admin/index");
		registry.addViewController("/admin/main/").setViewName("/admin/main");
	}
}
