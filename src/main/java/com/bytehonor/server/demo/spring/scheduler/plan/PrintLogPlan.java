package com.bytehonor.server.demo.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.lang.spring.thread.SafeRunner;
import com.bytehonor.sdk.server.spring.scheduler.plan.TimeCronPlan;
import com.bytehonor.sdk.server.spring.scheduler.time.TimeCron;
import com.bytehonor.sdk.server.spring.scheduler.time.TimeCronBuilder;
import com.bytehonor.server.demo.spring.scheduler.task.PrintLogTask;

public class PrintLogPlan extends TimeCronPlan {

    private List<TimeCron> timeCrons;

    public PrintLogPlan() {
        timeCrons = new ArrayList<TimeCron>();
        timeCrons.addAll(TimeCronBuilder.make().build());
    }

    @Override
    public List<TimeCron> crons() {
        return timeCrons;
    }

    @Override
    public SafeRunner create(LocalDateTime ldt) {
        return new PrintLogTask(ldt);
    }

}
