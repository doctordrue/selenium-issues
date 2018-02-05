package com.doctordrue.java.selenium.tests.issues.chromedriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.doctordrue.java.selenium.core.BaseTest;

public class WaitFrameIssue extends BaseTest {

    @BeforeTest
    public void openJsFiddle() {
	driver.get("http://jsfiddle.net/1ma65843/5/");
	new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("result"));
    }

    @Test
    public void switchToFrameAndFindElement() throws InterruptedException {
	driver.findElement(By.id("clickme")).click();
	new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("target_iframe"));
	// Small timeout below fixes issue
	// Thread.sleep(1000);
	driver.findElement(By.id("menu_projects")).click();
    }

}
