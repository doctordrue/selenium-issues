package com.doctordrue.java.selenium.tests.issues.ie.memory_leak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.doctordrue.java.selenium.core.BaseTestNGTest;

public class AngularSPAppTest extends BaseTestNGTest {
    public int i = 0;

    @BeforeSuite
    public void setConfig() {
	System.setProperty("browser", "IE");
    }

    @BeforeGroups({ "empty", "id", "name", "css", "xpath" })
    public void openPage() throws InterruptedException {
	driver.get("https://jsfiddle.net/dyyh2yzt/3/");
	Thread.sleep(5000);
	driver.switchTo().frame("result");
    }

    @Test(groups = "empty", invocationCount = 1000)
    public void emptyTest() throws InterruptedException {
	Thread.sleep(50);
    }

    @Test(groups = "id", invocationCount = 10000, dependsOnGroups = "empty", alwaysRun = true)
    public void findById() {
	driver.findElement(By.id("item-input"));
    }

    @Test(groups = "name", invocationCount = 10000, dependsOnGroups = "id", alwaysRun = true)
    public void findByName() {
	driver.findElement(By.name("item-input"));
    }

    @Test(groups = "css", invocationCount = 10000, dependsOnGroups = "name", alwaysRun = true)
    public void findByCssSelector() {
	driver.findElement(By.cssSelector("input.w3-input.ng-valid"));
    }

    @Test(groups = "xpath", invocationCount = 10000, dependsOnGroups = "css", alwaysRun = true)
    public void findByXpath() {
	driver.findElement(By.xpath("//input[@ng-model='addMe']"));
    }

    // @Test(invocationCount = 10000, dependsOnMethods = "findByXpath",
    // alwaysRun = true)
    public void simpleTest() {
	WebElement input = driver.findElement(By.xpath("//input[@ng-model='addMe']"));
	input.clear();
	String text = "item " + i++;
	input.sendKeys(text);
	WebElement button = driver.findElement(By.xpath("//button[@ng-click = 'addItem()']"));
	button.click();
	WebElement item = driver
		.findElement(By.xpath(String.format("//li[@ng-repeat = 'x in products'][text() = '%s']", text)));
	item.findElement(By.xpath(".//span[@ng-click='removeItem($index)']")).click();
    }

    @AfterGroups({ "empty", "id", "name", "css", "xpath" })
    public void closeDriver() {
	driver.quit();
	setDriver();
    }
}
