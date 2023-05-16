package com.example.mvcexample.config;

import com.example.mvcexample.filter.LoggingFilter;
import com.example.mvcexample.interceptor.CustomRequestInterceptor;
import com.example.mvcexample.listener.CustomSessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter(){
        FilterRegistrationBean<LoggingFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoggingFilter());
        registrationBean.addUrlPatterns("/example");

        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomRequestInterceptor());
    }

    @Bean
    public ServletListenerRegistrationBean<CustomSessionListener> sessionListener() {
        return new ServletListenerRegistrationBean<>(new CustomSessionListener());
    }
}
