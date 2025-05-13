package com.example.mycoffee;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all paths
                .allowedOrigins("http://mycoffeems.s3-website-us-west-1.amazonaws.com") // Specify allowed origin
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}