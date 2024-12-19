package com.bytehonor.demo.selenium.spring.driver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class ChromeSample2 {

    private static final Logger LOG = LoggerFactory.getLogger(ChromeSample2.class);

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36";

    public static void test() {
        System.setProperty("webdriver.chrome.driver", "D:/Program/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--user-agent=" + USER_AGENT);
        long now = 0L;
        long start = 0L;

        start = System.currentTimeMillis();
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://vue.bytehonor.com/user/profile/62eea88d201601bfc592209554e57964");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));
        driver.manage().timeouts().scriptTimeout(Duration.ofMillis(1000L));
        now = System.currentTimeMillis();
        LOG.info("1 get cost:{}", now - start);

        Sleep.millis(500L);

        start = System.currentTimeMillis();
        find(driver);
        now = System.currentTimeMillis();
        LOG.info("2 find cost:{}", now - start);

        Sleep.millis(3000L);

        start = System.currentTimeMillis();
        driver.navigate().refresh();
        now = System.currentTimeMillis();
        LOG.info("3 refresh cost:{}", now - start);

        start = System.currentTimeMillis();
        find(driver);
        now = System.currentTimeMillis();
        LOG.info("4 find cost:{}", now - start);

        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            LOG.info("name:{}, value:{}, expiry:{}", cookie.getName(), cookie.getValue(), cookie.getExpiry());
        }

        Sleep.millis(TimeConstants.MINUTE);
    }

    private static void find(WebDriver driver) {
        WebElement element = driver.findElement(By.tagName("h2"));
        LOG.info("element:{}", element.getText());
    }

    public static void main(String[] args) {
        test();
    }
}
