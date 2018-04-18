package com.doctordrue.java.selenium.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    protected WebDriver driver;
    protected DriverType driverType = DriverType.IE;

    @BeforeTest
    public void setDriver() {
	driver = DriverFactory.getDriver(Configuration.get().browser());
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
    }

    @AfterTest(alwaysRun = true)
    public void quit() {
	if (driver != null) {
	    driver.quit();
	}
    }
}
