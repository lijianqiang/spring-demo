package com.bytehonor.server.demo.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.base.spring.response.DataString;
import com.bytehonor.sdk.lang.spring.core.Randomizer;

@RestController
@RequestMapping("/error")
public class ErrorController {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("/test")
    public DataString test() {
        LOG.info("test");
        int rand = Randomizer.integer(1, 9);
        if (rand % 2 == 0) {
            throw new RuntimeException("error");
        }
        return DataString.ok();
    }
}
