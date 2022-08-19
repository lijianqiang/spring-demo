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
        if (LOG.isDebugEnabled()) {
            LOG.debug("subject:{}, bdoy:{}", payload.getSubject(), payload.getBody());
        }

        SampleEvent event = payload.reflect(SampleEvent.class);
        LOG.info("id:{}", event.getId());
    }

}
