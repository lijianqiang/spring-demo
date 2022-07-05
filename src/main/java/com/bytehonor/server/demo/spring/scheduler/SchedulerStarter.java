package com.bytehonor.server.demo.spring.scheduler;

import com.bytehonor.sdk.server.spring.scheduler.SchedulerPlanStarter;
import com.bytehonor.server.demo.spring.scheduler.lock.RedisTaskLocker;
import com.bytehonor.server.demo.spring.scheduler.plan.PrintLogPlan;

public class SchedulerStarter {

    public static void init() {

        SchedulerPlanStarter.start(1, new RedisTaskLocker());

        SchedulerPlanStarter.add(new PrintLogPlan());
    }
}
