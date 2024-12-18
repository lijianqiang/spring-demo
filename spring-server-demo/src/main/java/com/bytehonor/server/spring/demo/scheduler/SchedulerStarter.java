package com.bytehonor.server.spring.demo.scheduler;

import com.bytehonor.sdk.server.spring.scheduler.SpringScheduler;
import com.bytehonor.server.spring.demo.scheduler.lock.RedisTaskLocker;
import com.bytehonor.server.spring.demo.scheduler.plan.PrintLogPlan;

public class SchedulerStarter {

    public static void init() {

        SpringScheduler.start(new RedisTaskLocker());

        SpringScheduler.add(new PrintLogPlan());
    }
}
