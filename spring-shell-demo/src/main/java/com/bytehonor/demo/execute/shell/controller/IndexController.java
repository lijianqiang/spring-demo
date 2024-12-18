package com.bytehonor.demo.execute.shell.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @ResponseBody
    @GetMapping("/")
    public String actionIndex(HttpServletRequest request) {
        LOG.info("hello {}, {}", applicationName, serverPort);
        StringBuilder sb = new StringBuilder();
        sb.append(applicationName).append(":").append(serverPort);
        return sb.toString();
    }

}
