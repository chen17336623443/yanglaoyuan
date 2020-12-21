package com.yanglaoyuan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebInterConfig implements WebMvcConfigurer {

    @Bean
    public TokenInterceptor WebMvcUtil() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(WebMvcUtil())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/**","/error/**");
        /*"/user/login/**", */
    }

}
