package com.kutlu.kickstatz.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

    @Value("${chromedriver.path}")
    private String chromeDriverPath;

    @PostConstruct
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @Bean
    public WebDriver webDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"); // Gerçek bir tarayıcı user-agent'ı
        return new ChromeDriver(options);
    }
}