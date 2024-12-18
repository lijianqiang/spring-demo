package com.bytehonor.server.spring.demo.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.base.spring.response.DataString;
import com.bytehonor.sdk.lang.spring.util.TimeFormatUtils;
import com.bytehonor.sdk.server.spring.SpringServer;

@RestController
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public DataString actionIndex() {
        LOG.info("hello, {}", SpringServer.id());
        return new DataString("hello world");
    }

    @GetMapping("/time")
    public DataString actionTime() {
        String format = TimeFormatUtils.format(LocalDateTime.now());
        LOG.info("time:{}", format);
        return new DataString(format);
    }

}
