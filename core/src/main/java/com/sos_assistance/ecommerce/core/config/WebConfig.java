package com.sos_assistance.ecommerce.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.allowedOrigins:all}")
    private String allowedOrigins;

    @Value("${cors.allowedMethods:all}")
    private String allowedMethods;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] origins = allowedOrigins.equals("all") ? new String[]{"*"} : allowedOrigins.replace(" ", "").split(",");
        String[] methods = allowedMethods.equals("all") ? new String[]{"*"} : allowedMethods.replace(" ", "").split(",");
        registry.addMapping("/**")
                .allowedOrigins(origins)
                .allowedMethods(methods);
    }
}
