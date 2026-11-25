package com.devops.tests;

import com.devops.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {
    @Test
    public void navigationShouldOpenAboutAndReturnHome() {
        driver.findElement(By.cssSelector("[data-testid='nav-about']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-testid='about-heading']")).isDisplayed());

        driver.findElement(By.cssSelector("[data-testid='nav-home']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-testid='home-heading']")).isDisplayed());
    }
}
