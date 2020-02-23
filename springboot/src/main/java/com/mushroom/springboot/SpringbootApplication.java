package com.mushroom.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootApplication {
    public static void main(String[] args) {
        //new SpringApplicationBuilder(SpringbootApplication.class).web(WebApplicationType.NONE).run(args);
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
