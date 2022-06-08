package com.bytehonor.server.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bytehonor.sdk.server.spring.annotation.SpringBootStandard;

@SpringBootStandard
@SpringBootApplication
public class SpringDemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoServerApplication.class, args);
    }

}
