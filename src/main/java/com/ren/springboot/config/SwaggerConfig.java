package com.ren.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private ApiInfo createAI() {
        return new ApiInfoBuilder().title("测试一下springboot")
                .description("只是一个测试")
                .version("0.1")
                .contact(new Contact("renaaa", "http://renaaa.xyz", "ren812848195@163.com"))
                .build();
    }

    @Bean
    public Docket createD() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createAI()).select()
                .apis(RequestHandlerSelectors.basePackage("com.ren.springboot"))
                .build();
    }
}
