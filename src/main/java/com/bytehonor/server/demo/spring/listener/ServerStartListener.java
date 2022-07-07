package com.bytehonor.server.demo.spring.listener;

import com.bytehonor.sdk.server.spring.start.SpringServerListener;
import com.bytehonor.server.demo.spring.scheduler.SchedulerStarter;

public class ServerStartListener implements SpringServerListener {

    @Override
    public void onStart() {
        SchedulerStarter.init();
    }
}
