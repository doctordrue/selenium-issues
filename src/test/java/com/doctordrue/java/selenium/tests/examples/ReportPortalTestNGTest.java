package com.doctordrue.java.selenium.tests.examples;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.doctordrue.java.selenium.core.BaseTestNGTest;
import com.epam.reportportal.testng.ReportPortalTestNGListener;

/**
 * @author Andrey_Barantsev
 * 1/22/2020
 **/
@Listeners(ReportPortalTestNGListener.class)
public class ReportPortalTestNGTest extends BaseTestNGTest {

   private static final Logger LOGGER = Logger.getLogger(ReportPortalTestNGTest.class);

   @BeforeTest
   public void openPage() {
      LOGGER.info("We are in BeforeTest");
      driver.get("http://fiddle.jshell.net/m7j8e28n/4/show/");
   }

   @Test
   public void test() {
      LOGGER.info("We are in test");
   }

}
