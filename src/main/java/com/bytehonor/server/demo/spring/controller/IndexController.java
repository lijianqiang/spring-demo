package com.bytehonor.server.demo.spring.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.lang.spring.util.TimeFormatUtils;
import com.bytehonor.sdk.server.spring.SpringServer;
import com.bytehonor.sdk.server.spring.web.model.DataString;

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
