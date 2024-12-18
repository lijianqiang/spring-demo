package com.bytehonor.server.spring.demo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.event.spring.consumer.AbstractEventConsumer;
import com.bytehonor.sdk.server.spring.SpringServer;

public class SampleEventConsumer extends AbstractEventConsumer<SampleEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(SampleEventConsumer.class);

    private final SamleEventService samleEventService;

    public SampleEventConsumer() {
        this.samleEventService = SpringServer.bean(SamleEventService.class);
    }

    @Override
    public Class<SampleEvent> target() {
        return SampleEvent.class;
    }

    @Override
    public void process(SampleEvent payload) {
        LOG.info("event:{} begin", payload.getId());
        RateLimitedExecutor.apply(2000L, payload, samleEventService::consume);
        LOG.info("event:{} end", payload.getId());
    }

}
