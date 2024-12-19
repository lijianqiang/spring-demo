package com.bytehonor.demo.selenium.spring.driver.parser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BytehonorPageParser implements PageParser {

    private static final Logger LOG = LoggerFactory.getLogger(BytehonorPageParser.class);

    @Override
    public void parse(WebDriver driver) {
        WebElement element = driver.findElement(By.tagName("h2"));
        LOG.info("element:{}", element.getText());
    }
}
