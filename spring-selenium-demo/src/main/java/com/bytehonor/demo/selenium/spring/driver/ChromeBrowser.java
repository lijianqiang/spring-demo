package com.bytehonor.demo.selenium.spring.driver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeBrowser {

    private static final Logger LOG = LoggerFactory.getLogger(ChromeBrowser.class);

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36";

    public static WebDriver driver(String url) {
        System.setProperty("webdriver.chrome.driver", "D:/Program/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--user-agent=" + USER_AGENT);

        long start = System.currentTimeMillis();
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));
        driver.manage().timeouts().scriptTimeout(Duration.ofMillis(1000L));
        LOG.info("driver cost:{}", System.currentTimeMillis() - start);
        return driver;
    }

    public static void cookie(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            LOG.info("name:{}, value:{}, expiry:{}", cookie.getName(), cookie.getValue(), cookie.getExpiry());
        }
    }

}
