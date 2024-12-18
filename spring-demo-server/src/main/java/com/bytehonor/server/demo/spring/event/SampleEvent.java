package com.bytehonor.server.demo.spring.event;

import java.io.Serializable;

public class SampleEvent implements Serializable {

    private static final long serialVersionUID = -356390135068553977L;

    private int id;

    public SampleEvent() {
        this(0);
    }
    public SampleEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
