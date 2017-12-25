package com.doctordrue.java.selenium.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    protected WebDriver driver;
    protected DriverType driverType = DriverType.CHROME;

    @BeforeTest
    public void setDriver() {
	driver = DriverFactory.getDriver(driverType);
    }

    @AfterTest
    public void quit() {
	if (driver != null) {
	    driver.quit();
	}
    }
}
