package com.bytehonor.demo.execute.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bytehonor.sdk.server.spring.annotation.ServerStandard;

@ServerStandard
@SpringBootApplication
public class SpringShellDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringShellDemoApplication.class, args);
	}

}
