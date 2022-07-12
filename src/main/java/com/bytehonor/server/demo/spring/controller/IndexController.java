package com.bytehonor.server.demo.spring.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.define.spring.result.StringResultVO;
import com.bytehonor.sdk.lang.spring.util.LocalDateTimeUtils;
import com.bytehonor.sdk.server.spring.SpringServer;

@RestController
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public StringResultVO actionIndex() {
        LOG.info("hello, {}", SpringServer.id());
        return new StringResultVO("hello world");
    }

    @RequestMapping("/time")
    public StringResultVO actionTime() {
        String format = LocalDateTimeUtils.format(LocalDateTime.now());
        LOG.info("time:{}", format);
        return new StringResultVO(format);
    }

}
