package com.bytehonor.server.demo.spring.event;

import com.bytehonor.sdk.event.spring.SpringEvent;

public class SpringEventStarter {

    public static void init() {
        SpringEvent.consumer(new SampleEventConsumer());
    }
}
