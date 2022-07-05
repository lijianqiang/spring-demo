package com.bytehonor.server.demo.spring.scheduler.task;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeRunner;

public class PrintLogTask extends SafeRunner {

    private static final Logger LOG = LoggerFactory.getLogger(PrintLogTask.class);

    private final LocalDateTime ldt;

    public PrintLogTask(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    @Override
    public void runInSafe() {
        LOG.info("ldt:{}", ldt);
    }

}
