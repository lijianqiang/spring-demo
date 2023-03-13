package com.bytehonor.server.demo.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.event.spring.consumer.AbstractEventConsumer;

public class SampleEventConsumer extends AbstractEventConsumer<SampleEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(SampleEventConsumer.class);

    public SampleEventConsumer() {
        super(1024);
    }

    @Override
    public Class<SampleEvent> target() {
        return SampleEvent.class;
    }

    @Override
    public void process(SampleEvent payload) {
        LOG.info("id:{}", payload.getId());
    }

}
