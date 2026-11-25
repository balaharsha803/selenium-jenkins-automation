package com.devops.tests;

import com.devops.base.BaseTest;
import com.devops.pages.FeedbackPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormValidationTests extends BaseTest {
    @Test
    public void requiredFieldsShouldPreventEmptySubmission() {
        FeedbackPage page = new FeedbackPage(driver, wait);
        page.openFeedback();
        Assert.assertTrue(page.isNameRequired());
        Assert.assertTrue(driver.findElement(By.id("feedback-name")).getAttribute("required") != null);
        Assert.assertEquals(driver.findElement(By.id("feedback-name")).getAttribute("value"), "");
    }

    @Test
    public void validFeedbackShouldBeSubmitted() {
        FeedbackPage page = new FeedbackPage(driver, wait);
        page.openFeedback();
        page.fillForm("Test User", "test@example.com", "Automated feedback test");
        page.submit();
        Assert.assertEquals(page.getResult(), "Feedback submitted successfully");
    }
}
