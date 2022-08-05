package com.bytehonor.server.demo.spring.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.define.spring.response.DataString;
import com.bytehonor.sdk.lang.spring.util.TimeFormatUtils;
import com.bytehonor.sdk.message.server.event.EventPayload;
import com.bytehonor.sdk.message.server.producer.ServerEventProducer;
import com.bytehonor.sdk.server.spring.SpringServer;

@RestController
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ServerEventProducer serverEventProducer;

    @RequestMapping("/")
    public DataString actionIndex() {
        LOG.info("hello, {}", SpringServer.id());
        return new DataString("hello world");
    }

    @RequestMapping("/time")
    public DataString actionTime() {
        String format = TimeFormatUtils.format(LocalDateTime.now());
        LOG.info("time:{}", format);
        return new DataString(format);
    }

    @RequestMapping("/test")
    public DataString actionTest() {
        LOG.info("actionTest");
        EventPayload payload = EventPayload.build("actionTest");
        serverEventProducer.produce(payload);
        return DataString.ok();
    }

}
