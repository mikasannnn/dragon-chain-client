package com.mikasan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

@Slf4j
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 允许任何来源的请求，解决其他主机连接问题
        config.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:*",     // 本地开发环境
                "http://127.0.0.1:*",     // 本地回环地址
                "http://192.168.31.88:5173",  // 特定前端开发地址
                "http://10.138.50.58:9090",   // 特定前端地址
                "http://*:8080",          // 任何主机的8080端口
                "http://*:*",             // 任何HTTP源和端口
                "https://*:*"             // 任何HTTPS源和端口
        ));

        // 允许的方法
        config.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));

        // 允许的Headers
        config.setAllowedHeaders(Arrays.asList(
                "Content-Type",
                "Authorization",
                "X-Requested-With",
                "Accept",
                "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "X-Auth-Token"
        ));

        // 暴露的Headers
        config.setExposedHeaders(Arrays.asList(
                "X-Auth-Token",
                "Content-Type",
                "Authorization"
        ));

        config.setAllowCredentials(true); // 允许携带凭证
        config.setMaxAge(3600L); // 预检请求缓存时间

        // 调试日志
        log.debug("CORS Config: Allowed Origins: {}", config.getAllowedOrigins());
        log.debug("CORS Config: Allowed Methods: {}", config.getAllowedMethods());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 全路径匹配
        return new CorsFilter(source);
    }

}