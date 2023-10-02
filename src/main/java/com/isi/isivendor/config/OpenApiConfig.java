package com.isi.isivendor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("REST API - With java 20 and Spring Boot 3")
                        .version("v1")
                        .description("Project for learning and improvement")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0")
                                .url("https://resume-app-mwrk.vercel.app/")
                        )
                );
    }

}