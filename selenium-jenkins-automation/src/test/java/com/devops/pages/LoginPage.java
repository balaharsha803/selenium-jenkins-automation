package com.devops.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.cssSelector("[data-testid='login-button']");
    private final By message = By.id("login-message");
    private final By dashboard = By.cssSelector("[data-testid='dashboard']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void login(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
    }

    public String getMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }

    public boolean isDashboardVisible() {
        return driver.findElement(dashboard).isDisplayed();
    }
}
