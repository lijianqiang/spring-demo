package com.bytehonor.demo.selenium.spring.driver;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class ChromeSample {

    private static final Logger LOG = LoggerFactory.getLogger(ChromeSample.class);

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36";

    public static void test() {
        System.setProperty("webdriver.chrome.driver", "D:/Program/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--user-agent=" + USER_AGENT);
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.binance.com/zh-CN/support/announcement");
        wait(driver, 500L);
        find(driver);
        wait(driver, 3000L);
        driver.navigate().refresh();
        find(driver);
        
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            LOG.info("name:{}, value:{}, expiry:{}", cookie.getName(), cookie.getValue(), cookie.getExpiry());
        }

        Sleep.millis(TimeConstants.MINUTE);
    }

    private static void find(WebDriver driver) {
//        WebElement element = driver.findElement(By.tagName("h2"));
//        LOG.info("element:{}", element.getText());
        String page = driver.getPageSource();
        LOG.info("page:{}", page);
    }

    public static void wait(WebDriver driver, long millis) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(millis));
//        try {
//            wait.wait();
//        } catch (InterruptedException e) {
//            LOG.error("wait error", e);
//        }
        Sleep.millis(millis);
    }

    public static void main(String[] args) {
        test();
    }
}
