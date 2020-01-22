package com.doctordrue.java.selenium.core.ui.config;

import com.doctordrue.java.selenium.core.DriverType;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:test.properties")
public interface UITestConfig extends Config {
    @DefaultValue("CHROME")
    DriverType browser();

    @Key("ie.requireFocus")
    @DefaultValue("true")
    boolean requireFocus();
    
    @Key("canary.binary")
    String canaryBinary();
}
