package com.lei.tang.config;

import com.lei.tang.interceptors.OneInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;

/**
 * @author tanglei
 * @date 18/9/28
 */
@Configuration
public class WebMVCConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OneInterceptor()).addPathPatterns(Arrays.asList("/redis/*","/user/*"));
        super.addInterceptors(registry);
    }
}
