package com.mikasan.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("hello-knife4j项目API")
                        .version("1.0")
                        .description("hello-knife4j项目的接口文档"));
    }
    
    @Bean
    public GroupedOpenApi registerAPI() {
        return GroupedOpenApi.builder().group("报告管理").
                pathsToMatch("/report/**").
                build();
    }


}