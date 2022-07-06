package com.bytehonor.server.demo.spring.scheduler;

import com.bytehonor.sdk.server.spring.scheduler.SpringScheduler;
import com.bytehonor.server.demo.spring.scheduler.lock.RedisTaskLocker;
import com.bytehonor.server.demo.spring.scheduler.plan.PrintLogPlan;

public class SchedulerStarter {

    public static void init() {

        SpringScheduler.start(1, new RedisTaskLocker());

        SpringScheduler.add(new PrintLogPlan());
    }
}
