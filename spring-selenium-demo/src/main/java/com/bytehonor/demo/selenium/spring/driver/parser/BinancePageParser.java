package com.bytehonor.demo.selenium.spring.driver.parser;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.crawler.binance.constant.BinanceCrawlerLang;
import com.bytehonor.sdk.crawler.binance.model.BinanceAnnounceItem;
import com.bytehonor.sdk.crawler.binance.parser.BinanceAnnounceParser;
import com.bytehonor.sdk.lang.spring.util.TimeFormatUtils;

public class BinancePageParser implements PageParser {

    private static final Logger LOG = LoggerFactory.getLogger(BinancePageParser.class);

    @Override
    public void parse(WebDriver driver) {
//        String html = driver.getPageSource(); // 100次 7958ms
//        List<BinanceAnnounceItem> items = BinanceAnnounceParser.html(html, BinanceCrawlerLang.ZH_CN);

        WebElement element = driver.findElement(By.id("__APP_DATA")); // 100次 2779ms
        String json = element.getAttribute("innerHTML"); // element.getText();
        List<BinanceAnnounceItem> items = BinanceAnnounceParser.appData(json, BinanceCrawlerLang.ZH_CN);
        for (BinanceAnnounceItem item : items) {
            item(item);
        }
    }

    public static void item(BinanceAnnounceItem item) {
        LOG.info("time:{}, lang:{}, {}, category:{}/{}, code:{}, url:{}", TimeFormatUtils.format(item.getPublishAt()),
                item.getLang(), item.getTitle(), item.getCategoryId(), item.getCategoryName(), item.getCode(),
                item.getUrl());
    }
}
