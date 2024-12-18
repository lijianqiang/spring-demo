package com.bytehonor.server.spring.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.base.spring.response.DataString;

@RestController
@RequestMapping("/error")
public class ErrorController {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("/test")
    public DataString test() {
        LOG.info("test");
        error();
        return DataString.ok();
    }

    public static void error() {
        throw new RuntimeException("throw_error");
    }
}
