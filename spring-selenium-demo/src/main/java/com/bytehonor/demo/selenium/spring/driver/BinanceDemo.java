package com.bytehonor.demo.selenium.spring.driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.demo.selenium.spring.driver.parser.BinancePageParser;
import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class BinanceDemo {

    private static final Logger LOG = LoggerFactory.getLogger(BinanceDemo.class);

    public static void main(String[] args) {
        WebDriver driver = ChromeBrowser.driver("https://www.binance.com/zh-CN/support/announcement");
        Sleep.millis(500L);
        driver.navigate().refresh();
        Sleep.millis(500L);
        BinancePageParser parser = new BinancePageParser();

//        WebElement element = driver.findElement(By.id("__APP_DATA"));
//        LOG.info("tag:{}, text:{}, html:{}", element.getTagName(), element.getText(), element.getAttribute("innerHTML"));
        
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            parser.parse(driver);
        }
        LOG.info("cost:{}", System.currentTimeMillis() - start);

        Sleep.millis(TimeConstants.HOUR_HALF);
    }
}
