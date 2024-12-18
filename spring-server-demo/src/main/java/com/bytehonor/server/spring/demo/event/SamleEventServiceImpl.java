package com.bytehonor.server.spring.demo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bytehonor.sdk.lang.spring.thread.Sleep;

@Service("samleEventService")
public class SamleEventServiceImpl implements SamleEventService {

    private static final Logger LOG = LoggerFactory.getLogger(SamleEventServiceImpl.class);

    @Override
    public void consume(SampleEvent event) {
        LOG.info("event:{} run", event.getId());
        Sleep.millis(500L);
    }

}
