package com.bytehonor.server.demo.spring.event;

import java.io.Serializable;

public class SampleEvent implements Serializable {

    private static final long serialVersionUID = -356390135068553977L;

    private long time;

    public SampleEvent() {
        this.time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
