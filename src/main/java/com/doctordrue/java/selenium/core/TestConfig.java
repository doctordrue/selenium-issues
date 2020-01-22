package com.doctordrue.java.selenium.core;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:test.properties")
public interface TestConfig extends Config {
    @DefaultValue("CHROME")
    DriverType browser();

    @Key("ie.requireFocus")
    @DefaultValue("true")
    boolean requireFocus();
    
    @Key("canary.binary")
    String canaryBinary();
}
