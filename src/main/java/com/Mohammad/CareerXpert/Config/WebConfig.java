package com.Mohammad.CareerXpert.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**", "/uploads.profile/**", "/CommunityUploads/**")
                .addResourceLocations("file:uploads/", "file:/uploads.profile/", "file:CommunityUploads/");
    }
}
