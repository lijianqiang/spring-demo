package com.bytehonor.server.demo.spring.event;

import com.bytehonor.sdk.event.spring.consumer.SpringEventConsumer;

public class SpringEventStarter {

    public static void init() {
        SpringEventConsumer.add(new SampleEventConsumer());
    }
}
