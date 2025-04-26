package com.kutlu.kickstatz.crawler;

import com.kutlu.kickstatz.model.ChannelResponse;
import com.kutlu.kickstatz.service.KickApiService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<ChannelResponse> scrapeTopChannels() {
        List<ChannelResponse> channels = new ArrayList<>();
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

            List<WebElement> channelElements = webDriver.findElements(By.cssSelector("div[class*='group/card'] a"));

            for (WebElement element : channelElements) {
                WebElement titleElement = element.findElement(By.cssSelector("a[title]"));
                String title = titleElement.getAttribute("title");
                log.info("Scraping channel: {}", title);

                ChannelResponse response = kickApiService.getChannelBySlug(title);

                if (response == null || response.getData() == null || response.getData().isEmpty()) {
                    log.warn("No data found for slug: {}", title);
                    continue;
                } else {
                    channels.add(response);
                }
            }

            /*List<WebElement> channelElements = webDriver.findElements(By.cssSelector("a[data-testid='browse-card-link']"));

            for (WebElement element : channelElements) {
                String outerHtml = element.getAttribute("outerHTML");
                assert outerHtml != null;
                Document doc = Jsoup.parse(outerHtml);

                Element link = doc.selectFirst("a[data-testid='browse-card-link']");
                String slug = link != null ? link.attr("href").replace("/", "") : null;
                log.info("Slug: {}", slug);

                Element titleElement = doc.selectFirst("[data-testid='browse-card-title']");
                String title = titleElement != null ? titleElement.text() : null;
                log.info("Title: {}", title);

                Element categoryElement = doc.selectFirst("[data-testid='browse-card-category']");
                String category = categoryElement != null ? categoryElement.text() : null;
                log.info("Category: {}", category);

                Element viewerElement = doc.selectFirst("[data-testid='browse-card-viewers']");
                String viewersText = viewerElement != null ? viewerElement.text().replace(" Viewers", "").replace(",", "") : "0";
                int viewerCount = Integer.parseInt(viewersText);
                log.info("Viewers: {}", viewerCount);

                ChannelResponse response = kickApiService.getChannelBySlug(slug);

                if (response == null || response.getData() == null || response.getData().isEmpty()) {
                    log.warn("No data found for slug: {}", slug);
                    continue;
                }
                channels.add(response);
            }*/
        } catch (Exception e) {
            log.error("Error while scraping channels: {}", e.getMessage(), e);
        }
        return channels;
    }
}
