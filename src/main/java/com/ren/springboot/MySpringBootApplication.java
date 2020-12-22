package com.ren.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.ren.springboot.dao")
public class MySpringBootApplication {

        public static void main(String[] args) {
            SpringApplication.run(MySpringBootApplication.class, args);


        }

}
