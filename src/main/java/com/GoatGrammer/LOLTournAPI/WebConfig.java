package com.GoatGrammer.LOLTournAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Adjust the path as necessary
                .allowedOrigins("http://localhost:5173") // Specify your frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow necessary methods
                .allowCredentials(true) // Allow credentials (cookies, etc.)
                .allowedHeaders("*"); // You can specify allowed headers if needed
    }
}