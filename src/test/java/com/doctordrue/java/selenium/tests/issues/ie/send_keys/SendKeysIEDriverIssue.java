package com.doctordrue.java.selenium.tests.issues.ie.send_keys;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.doctordrue.java.selenium.core.BaseTestNGTest;

public class SendKeysIEDriverIssue extends BaseTestNGTest {

   @BeforeSuite
   public void setConfig() {
      System.setProperty("browser", "IE");
      System.setProperty("ie.requireFocus", "true");
   }

   @BeforeClass
   public void openUrl() {
      driver.get("https://jsfiddle.net/zm3p20yg/");
      new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("result"));
   }

   @DataProvider
   public Object[][] textProvider() {
      return new Object[][]{{"all lowercase text"}, {"ALL UPPERCASE TEXT"}, {"Mixed Text"}};
   }

   @Test(dataProvider = "textProvider")
   public void typeTypeIntoInputTest(String text) throws InterruptedException {
      WebElement element = driver.findElement(By.tagName("input"));
      element.click();
      element.clear();
      Thread.sleep(1500);
      element.sendKeys(text);

      String value = element.getAttribute("value");
      Assert.assertEquals(value, text);
   }

   @Test(dataProvider = "textProvider")
   public void actionsSendKeysTest(String text) throws InterruptedException {
      WebElement element = driver.findElement(By.tagName("input"));
      element.click();
      element.clear();
      new Actions(driver).sendKeys(text).build().perform();

      String value = element.getAttribute("value");
      Assert.assertEquals(value, text);
   }
}
