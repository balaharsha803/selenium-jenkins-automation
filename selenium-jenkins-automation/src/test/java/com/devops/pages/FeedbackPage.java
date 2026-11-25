package com.devops.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeedbackPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public FeedbackPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openFeedback() {
        driver.findElement(By.cssSelector("[data-testid='nav-feedback']")).click();
    }

    public void fillForm(String name, String email, String message) {
        driver.findElement(By.id("feedback-name")).sendKeys(name);
        driver.findElement(By.id("feedback-email")).sendKeys(email);
        driver.findElement(By.id("feedback-message")).sendKeys(message);
    }

    public void submit() {
        driver.findElement(By.cssSelector("[data-testid='feedback-submit']")).click();
    }

    public String getResult() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("feedback-result"))).getText();
    }

    public boolean isNameRequired() {
        return driver.findElement(By.id("feedback-name")).getAttribute("required") != null;
    }
}
