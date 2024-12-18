package com.bytehonor.demo.selenium.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.base.spring.response.DataString;
import com.bytehonor.sdk.server.spring.SpringServer;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @ResponseBody
    @GetMapping("/")
    public DataString actionIndex(HttpServletRequest request) {
        LOG.info("hello id:{}", SpringServer.id());
        return DataString.of("hello world");
    }

}
