package com.bytehonor.demo.selenium.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bytehonor.sdk.server.spring.annotation.ServerStandard;

@ServerStandard
@SpringBootApplication
public class SpringSeleniumDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSeleniumDemoApplication.class, args);
	}

}
