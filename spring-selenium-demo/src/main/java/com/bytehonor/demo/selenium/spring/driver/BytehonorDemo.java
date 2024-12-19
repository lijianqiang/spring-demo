package com.bytehonor.demo.selenium.spring.driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.demo.selenium.spring.driver.parser.BytehonorPageParser;
import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class BytehonorDemo {

    private static final Logger LOG = LoggerFactory.getLogger(BytehonorDemo.class);

    public static void main(String[] args) {
        WebDriver driver = ChromeBrowser
                .driver("https://vue.bytehonor.com/user/profile/62eea88d201601bfc592209554e57964");
        Sleep.millis(500L);

        BytehonorPageParser parser = new BytehonorPageParser();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Sleep.millis(1000L * 5);
            driver.navigate().refresh();
            parser.parse(driver);
        }
        LOG.info("cost:{}", System.currentTimeMillis() - start);

        Sleep.millis(TimeConstants.HOUR_HALF);
    }
}
