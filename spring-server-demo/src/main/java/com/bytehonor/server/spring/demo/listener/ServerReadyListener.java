package com.bytehonor.server.spring.demo.listener;

import org.springframework.stereotype.Component;

import com.bytehonor.sdk.server.spring.web.listener.ReadyListener;
import com.bytehonor.sdk.starter.jdbc.JdbcConfig;
import com.bytehonor.server.spring.demo.event.SpringEventStarter;
import com.bytehonor.server.spring.demo.scheduler.SchedulerStarter;

@Component
public class ServerReadyListener implements ReadyListener {

    @Override
    public void onStart() {
        SchedulerStarter.init();
        
        SpringEventStarter.init();
        
        JdbcConfig.setInfoEnabled(true);
    }
}
