package com.bytehonor.server.demo.spring.listener;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServerReadyListenerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServerReadyListenerTest.class);

    @Test
    public void test() {
        LOG.info("test");
    }

}
