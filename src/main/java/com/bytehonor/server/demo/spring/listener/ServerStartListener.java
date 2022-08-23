package com.bytehonor.server.demo.spring.listener;

import org.springframework.stereotype.Component;

import com.bytehonor.sdk.server.spring.listener.ReadyListener;
import com.bytehonor.server.demo.spring.event.SpringEventStarter;
import com.bytehonor.server.demo.spring.scheduler.SchedulerStarter;

@Component
public class ServerStartListener implements ReadyListener {

    @Override
    public void onStart() {
        SchedulerStarter.init();
        
        SpringEventStarter.init();
    }
}
