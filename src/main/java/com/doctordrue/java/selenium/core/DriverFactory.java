package com.doctordrue.java.selenium.core;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

   public static WebDriver getDriver(DriverType type) {
      switch (type) {
         case IE:
            return getInternetExplorerDriver();
         case FIREFOX:
            return getFirefoxLatestDriver();
         case CANARY:
            return getChromeCanaryDriver();
         case CHROME:
         default:
            return getChromeDriver();
      }
   }

   public static WebDriver getInternetExplorerDriver() {
      WebDriverManager.iedriver().arch32().setup();
      InternetExplorerOptions options = new InternetExplorerOptions();
      if (Configuration.getUIConfig().requireFocus()) {
         options.requireWindowFocus();
      }
      System.setProperty("webdriver.ie.driver.loglevel", "TRACE");
      System.setProperty("webdriver.ie.driver.logfile", "D:\\IEServerlog.log");
      return new InternetExplorerDriver(options);
   }

   public static WebDriver getChromeDriver() {
      WebDriverManager.chromedriver().arch32().setup();
      ChromeOptions options = new ChromeOptions();
      options.setExperimentalOption("excludeSwitches", Collections.singletonList("ignore-certificate-errors"));
      options.addArguments("--disable-extensions");
      options.addArguments("disable-infobars");

      Map<String, Object> prefs = new HashMap<String, Object>();
      prefs.put("credentials_enable_service", false);
      prefs.put("profile.password_manager_enabled", false);
      options.setExperimentalOption("prefs", prefs);
      //System.setProperty("webdriver.chrome.logfile", "D:\\chromedriver.log");
      //System.setProperty("webdriver.chrome.verboseLogging", "true");
      return new ChromeDriver(options);
   }

   public static WebDriver getChromeCanaryDriver() {
      WebDriverManager.chromedriver().useBetaVersions().arch32().setup();
      ChromeOptions options = new ChromeOptions();
      String path = Configuration.getUIConfig().canaryBinary();
      if (path == null || "".equals(path)) {
         path = System.getProperty("user.home") + "/AppData/Local/Google/Chrome SxS/Application/chrome.exe";
      }
      File binary = new File(path);
      options.setBinary(binary);
      options.setExperimentalOption("excludeSwitches", Collections.singletonList("ignore-certificate-errors"));
      options.addArguments("--disable-extensions");
      options.addArguments("disable-infobars");

      Map<String, Object> prefs = new HashMap<String, Object>();
      prefs.put("credentials_enable_service", false);
      prefs.put("profile.password_manager_enabled", false);
      options.setExperimentalOption("prefs", prefs);
      //System.setProperty("webdriver.chrome.logfile", "D:\\chromedriver.log");
      //System.setProperty("webdriver.chrome.verboseLogging", "true");
      return new ChromeDriver(options);
   }

   public static WebDriver getFirefoxLatestDriver() {
      WebDriverManager.firefoxdriver().useBetaVersions().setup();
      FirefoxOptions geckoOptions = new FirefoxOptions().addPreference("log", "{level: error}")
              .addPreference("dom.ipc.plugins.enabled.libflashplayer.so", true).addPreference("plugin.state.flash", 2)
              .setLogLevel(FirefoxDriverLogLevel.FATAL)
              .setBinary("C:\\Program Files\\Firefox Developer Edition\\firefox.exe");
      return new FirefoxDriver(geckoOptions);
   }
}
