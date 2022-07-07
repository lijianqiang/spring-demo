package com.bytehonor.server.demo.spring.listener;

import org.springframework.stereotype.Component;

import com.bytehonor.sdk.server.spring.start.SpringServerListener;
import com.bytehonor.server.demo.spring.scheduler.SchedulerStarter;

@Component
public class ServerStartListener implements SpringServerListener {

    @Override
    public void onStart() {
        SchedulerStarter.init();
    }
}
