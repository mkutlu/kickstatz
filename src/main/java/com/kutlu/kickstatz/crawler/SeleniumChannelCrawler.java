package com.kutlu.kickstatz.crawler;

import com.kutlu.kickstatz.model.ChannelDetailResponse;
import com.kutlu.kickstatz.service.KickApiService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SeleniumChannelCrawler {
    private final WebDriver webDriver;
    private final KickApiService kickApiService;

    public SeleniumChannelCrawler(WebDriver webDriver, KickApiService kickApiService) {
        this.webDriver = webDriver;
        this.kickApiService = kickApiService;
    }

    @Scheduled(fixedDelay = 60000)
    public List<ChannelDetailResponse> scrapeTopChannels() {
        List<ChannelDetailResponse> channels = new ArrayList<>();
        try {
            log.info("Scraping top channels...");
            webDriver.get("https://kick.com/browse?sort=viewers_high_to_low");

            // Wait for the page to load
            Thread.sleep(5000);  // Adjust as necessary for the page load

            // Scroll until the end of the page
            boolean endOfPage = false;
            while (!endOfPage) {
                // Get the current page height
                Object currentHeightObj = ((JavascriptExecutor) webDriver).executeScript("return document.body.scrollHeight");
                long currentHeight = currentHeightObj != null ? ((Number) currentHeightObj).longValue() : 0;

                // Scroll down
                ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

                // Wait for new channels to load
                Thread.sleep(2000); // Adjust the waiting time to ensure new channels load

                // Get the new page height after scrolling
                Object newHeightObj = ((JavascriptExecutor) webDriver).executeScript("return document.body.scrollHeight");
                long newHeight = newHeightObj != null ? ((Number) newHeightObj).longValue() : 0;

                // If the page height doesn't change, we've reached the bottom
                if (newHeight == currentHeight) {
                    endOfPage = true;
                }
            }

            // Find all <a> elements with a title attribute
            List<WebElement> elements = webDriver.findElements(By.cssSelector("a[title]"));

            Set<String> channelNames = new HashSet<>();
            // Print the text and title attribute of each element
            for (WebElement element : elements) {
                String channelName = Objects.requireNonNull(element.getAttribute("href"))
                        .substring(Objects.requireNonNull(element.getAttribute("href"))
                                .lastIndexOf("/") + 1);
                if(!channelNames.contains(channelName) && !channelName.isEmpty()) {
                    channelNames.add(channelName);
                    ChannelDetailResponse response = kickApiService.getChannelBySlug(channelName);

                    if (response == null) {
                        log.warn("No data found for slug: {}", channelName);
                    } else {
                        channels.add(response);
                    }
                }

            }
            log.info("Scraped {} channels", channels);
        } catch (Exception e) {
            log.error("Error while scraping channels: {}", e.getMessage(), e);
        }
        return channels;
    }
}
