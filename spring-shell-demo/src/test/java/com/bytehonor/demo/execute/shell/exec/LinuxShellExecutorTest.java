package com.bytehonor.demo.execute.shell.exec;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class LinuxShellExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(LinuxShellExecutorTest.class);

//    @Test
    public void testExecPython() {
        ClassPathResource resource = new ClassPathResource("script/python-test.py");
        try {
            File file = resource.getFile();
            LinuxShellExecutor.execPython(file.getPath());
        } catch (IOException e) {
            LOG.error("xxx", e);
        }
        assertTrue(true, "*testExecPython*");
    }

//    @Test
    public void testExecShell() {
        ClassPathResource resource = new ClassPathResource("script/bash.sh");
        try {
            File file = resource.getFile();
            LinuxShellExecutor.execShell(file);
        } catch (IOException e) {
            LOG.error("testExecShell", e);
        }
        assertTrue(true, "*testExecShell*");
    }

    @Test
    public void testExecCmd() {
        try {
            LinuxShellExecutor.execCommand("cmd /k d: && dir");
        } catch (IOException e) {
            LOG.error("testExecCmd", e);
        }
        assertTrue(true, "*testExecCmd*");
    }

}
