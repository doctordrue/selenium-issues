package com.doctordrue.java.selenium.core;

import org.aeonbits.owner.ConfigFactory;

public class Configuration {

    private static Configuration instance;
    private TestConfig testConfig;

    public Configuration() {
	testConfig = ConfigFactory.create(TestConfig.class, System.getProperties());
    }

    public static TestConfig get() {
	if (instance == null) {
	    instance = new Configuration();
	}
	return instance.testConfig;
    }
}
