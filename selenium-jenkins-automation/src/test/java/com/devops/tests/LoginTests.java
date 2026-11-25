package com.devops.tests;

import com.devops.base.BaseTest;
import com.devops.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void validLoginShouldDisplayDashboard() {
        LoginPage page = new LoginPage(driver, wait);
        page.login("demo", "Password123!");
        Assert.assertEquals(page.getMessage(), "Login successful");
        Assert.assertTrue(page.isDashboardVisible(), "Dashboard should be displayed after valid login");
    }

    @Test
    public void invalidLoginShouldBeRejected() {
        LoginPage page = new LoginPage(driver, wait);
        page.login("wrong-user", "wrong-password");
        Assert.assertEquals(page.getMessage(), "Invalid username or password");
        Assert.assertFalse(page.isDashboardVisible(), "Dashboard must not be displayed for invalid login");
    }
}
