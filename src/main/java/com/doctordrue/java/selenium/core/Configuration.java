package com.doctordrue.java.selenium.core;

import com.doctordrue.java.selenium.core.ui.config.UITestConfig;
import org.aeonbits.owner.ConfigFactory;

public class Configuration {

    private static Configuration instance;
    private UITestConfig uiTestConfig;

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
