package com.devops.base;

import com.devops.driver.DriverFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;

    @BeforeMethod
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://127.0.0.1:8080");
        driver = DriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (!result.isSuccess()) {
                saveFailureScreenshot(result.getMethod().getMethodName());
            }
            driver.quit();
        }
    }

    private void saveFailureScreenshot(String testName) {
        try {
            Path directory = Path.of("target", "screenshots");
            Files.createDirectories(directory);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(),
                    directory.resolve(testName + "-" + System.currentTimeMillis() + ".png"));
        } catch (IOException | RuntimeException exception) {
            System.err.println("Could not save failure screenshot: " + exception.getMessage());
        }
    }
}
