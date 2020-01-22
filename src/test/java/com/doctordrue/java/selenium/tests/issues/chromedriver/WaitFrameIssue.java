package com.doctordrue.java.selenium.tests.issues.chromedriver;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.doctordrue.java.selenium.core.BaseTestNGTest;

public class WaitFrameIssue extends BaseTestNGTest {
    
    @BeforeSuite
    public void setConfig() {
	System.setProperty("browser", "CHROME");
    }
    
    @BeforeTest
    public void openJsFiddle() {
	driver.get("http://fiddle.jshell.net/m7j8e28n/4/show/");
	new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
    }

    @Test(expectedExceptions = StaleElementReferenceException.class)
    public void switchToFrameAndFindElement() throws InterruptedException {
	driver.findElement(By.id("clickme")).click();
	new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("target_iframe"));
	WebElement link = driver.findElement(By.id("updatelink"));
	link.click();

	// Either delay to wait until new document loads into the iframe
	// Thread.sleep(2000);
	// or finding element on new page (due to implicitly waits)
	// driver.findElement(By.id("menu_projects")).click();
	// fixes the issue


	// Operation below leads to StaleElementReferenceException running on IE
	// or Firefox
	// But Chrome doesn't realize that document in frame are going to be
	// reloaded and so returns true
	boolean isDisplayed = link.isDisplayed();
	Assert.assertFalse(isDisplayed);
    }

}
