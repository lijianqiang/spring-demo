package com.bytehonor.server.demo.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.define.spring.response.DataString;
import com.bytehonor.sdk.event.spring.payload.EventPayload;
import com.bytehonor.sdk.event.spring.producer.SpringEventProducer;
import com.bytehonor.server.demo.spring.event.SampleEvent;

@RestController
@RequestMapping("/event")
public class EventController {

    private static final Logger LOG = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private SpringEventProducer springEventProducer;

    @RequestMapping("/test")
    public DataString test() {
        LOG.info("test");
        for (int i = 0; i < 10; i++) {
            EventPayload payload = EventPayload.build("actionTest" + i);
            springEventProducer.produce(payload);
        }
        return DataString.ok();
    }

    @RequestMapping("/test2")
    public DataString test2() {
        LOG.info("test2");
        for (int i = 0; i < 51200; i++) {
            EventPayload payload = EventPayload.build(new SampleEvent(i));
            springEventProducer.produce("spring-demo-server", payload);
        }
        return DataString.ok();
    }
}
