package com.devops.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {
    private DriverFactory() {}

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
                options.addArguments("-headless");
            }
            return new FirefoxDriver(options);
        }

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1440,1000");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        return new ChromeDriver(options);
    }
}
