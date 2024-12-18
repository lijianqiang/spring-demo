package com.bytehonor.demo.selenium.spring.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeSample {

    private static final Logger LOG = LoggerFactory.getLogger(ChromeSample.class);

    public static void test() {
        System.setProperty("webdriver.chrome.driver", "D:/Program/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://vue.bytehonor.com/user/profile/62eea88d201601bfc592209554e57964");
        WebElement element = driver.findElement(By.tagName("h2"));
        LOG.info("element:{}", element.getText());
    }

    public static void main(String[] args) {
        test();
    }
}
