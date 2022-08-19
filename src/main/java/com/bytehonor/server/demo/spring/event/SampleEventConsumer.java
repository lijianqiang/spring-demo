package com.bytehonor.server.demo.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.event.spring.consumer.EventConsumer;
import com.bytehonor.sdk.event.spring.payload.EventPayload;

public class SampleEventConsumer implements EventConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SampleEventConsumer.class);

    @Override
    public String subject() {
        return SampleEvent.class.getName();
    }

    @Override
    public void consume(EventPayload payload) {
        LOG.info("subject:{}, bdoy:{}", payload.getSubject(), payload.getBody());

        SampleEvent request = payload.reflect(SampleEvent.class);
        LOG.info("time:{}", request.getTime());
    }

}
