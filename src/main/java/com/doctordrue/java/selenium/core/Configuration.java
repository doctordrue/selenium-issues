package com.doctordrue.java.selenium.core;

import org.aeonbits.owner.ConfigFactory;

import com.doctordrue.java.selenium.core.ui.config.UITestConfig;

public class Configuration {

   private static Configuration instance;
   private final UITestConfig uiTestConfig;

   public Configuration() {
      uiTestConfig = ConfigFactory.create(UITestConfig.class, System.getProperties());
   }

   public static UITestConfig getUIConfig() {
      if (instance == null) {
         instance = new Configuration();
      }
      return instance.uiTestConfig;
   }
}
