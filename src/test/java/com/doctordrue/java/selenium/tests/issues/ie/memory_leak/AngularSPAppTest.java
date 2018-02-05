package com.doctordrue.java.selenium.tests.issues.ie.memory_leak;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class AngularSPAppTest {
    public WebDriver driver;
    private static final String BROWSER = "ie";
    public int i = 0;

    @BeforeGroups({ "empty", "id", "name", "css", "xpath" })
    public void setupDriver() throws InterruptedException {
	switch (BROWSER) {
	case "chrome":
	    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	    ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("excludeSwitches", Arrays.asList("ignore-certificate-errors"));
	    options.addArguments("--disable-extensions");
	    options.addArguments("disable-infobars");

	    Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    options.setExperimentalOption("prefs", prefs);
	    driver = new ChromeDriver(options);
	    break;
	case "ie":
	    System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
	    DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
	    ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		    false);
	    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
	    internetExplorerOptions
		    // .destructivelyEnsureCleanSession()
		    .requireWindowFocus().enableNativeEvents()
		    .takeFullPageScreenshot();

	    driver = new InternetExplorerDriver(internetExplorerOptions);
	    break;
	}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
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
    }
}
