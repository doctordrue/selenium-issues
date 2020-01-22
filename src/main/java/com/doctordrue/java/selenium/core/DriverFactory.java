package com.doctordrue.java.selenium.core;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverFactory {

    public static WebDriver getDriver(DriverType type) {
	switch (type) {
	case IE:
	    return getInternetExplorerDriver();
	case CHROME:
	    return getChromeDriver();
	case FIREFOX:
	    return getFirefoxLatestDriver();
	case CANARY:
	    return getChromeCanaryDriver();
	default:
	    return getChromeDriver();
	}
    }

    public static WebDriver getInternetExplorerDriver() {
	WebDriverManager.iedriver().arch32().version("3.8.0").setup();
	InternetExplorerOptions options = new InternetExplorerOptions();
	if (Configuration.getUIConfig().requireFocus()) {
	    options.requireWindowFocus();
	}
	System.setProperty("webdriver.ie.driver.loglevel", "TRACE");
	System.setProperty("webdriver.ie.driver.logfile", "D:\\IEServerlog.log");
	return new EventFiringWebDriver(new InternetExplorerDriver(options));
    }

    public static WebDriver getChromeDriver() {
	WebDriverManager.chromedriver().arch32().setup();
	ChromeOptions options = new ChromeOptions();
	options.setExperimentalOption("excludeSwitches", Arrays.asList("ignore-certificate-errors"));
	options.addArguments("--disable-extensions");
	options.addArguments("disable-infobars");

	Map<String, Object> prefs = new HashMap<String, Object>();
	prefs.put("credentials_enable_service", false);
	prefs.put("profile.password_manager_enabled", false);
	options.setExperimentalOption("prefs", prefs);
	//System.setProperty("webdriver.chrome.logfile", "D:\\chromedriver.log");
	//System.setProperty("webdriver.chrome.verboseLogging", "true");
	WebDriver driver = new EventFiringWebDriver(new ChromeDriver(options));
	return driver;
    }
    
    public static WebDriver getChromeCanaryDriver() {
	WebDriverManager.chromedriver().useBetaVersions().arch32().setup();
	ChromeOptions options = new ChromeOptions();
	String path = Configuration.getUIConfig().canaryBinary();
	if (path == null || "".equals(path)){
	    path = System.getProperty("user.home") + "/AppData/Local/Google/Chrome SxS/Application/chrome.exe";
	}
	File binary = new File(path);
	options.setBinary(binary);
	options.setExperimentalOption("excludeSwitches", Arrays.asList("ignore-certificate-errors"));
	options.addArguments("--disable-extensions");
	options.addArguments("disable-infobars");

	Map<String, Object> prefs = new HashMap<String, Object>();
	prefs.put("credentials_enable_service", false);
	prefs.put("profile.password_manager_enabled", false);
	options.setExperimentalOption("prefs", prefs);
	//System.setProperty("webdriver.chrome.logfile", "D:\\chromedriver.log");
	//System.setProperty("webdriver.chrome.verboseLogging", "true");
	WebDriver driver = new EventFiringWebDriver(new ChromeDriver(options));
	return driver;
    }

    public static WebDriver getFirefoxLatestDriver() {
	WebDriverManager.firefoxdriver().useBetaVersions().setup();
	FirefoxOptions geckoOptions = new FirefoxOptions().addPreference("log", "{level: error}")
		.addPreference("dom.ipc.plugins.enabled.libflashplayer.so", true).addPreference("plugin.state.flash", 2)
		.setLogLevel(FirefoxDriverLogLevel.FATAL)
		.setBinary("C:\\Program Files\\Firefox Developer Edition\\firefox.exe");
	WebDriver driver = new EventFiringWebDriver(new FirefoxDriver(geckoOptions));
	return driver;
    }
}
