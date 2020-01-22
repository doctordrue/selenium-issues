package com.doctordrue.java.selenium.tests.examples;

import com.epam.reportportal.junit5.ReportPortalExtension;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ReportPortalExtension.class)
public class ReportPortalJUnit5Test {
    private static final org.apache.log4j.Logger log = Logger.getLogger(ReportPortalJUnit5Test.class);

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        log.info("@BeforeEach - executes before each test method in this class");
    }

    @DisplayName("Single test successful")
    @Test
    void testSingleSuccessTest() {
        log.info("Success");
    }

    @Test
    @Disabled("Not implemented yet")
    void testShowSomething() {
    }

    @AfterEach
    void tearDown() {
        log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        log.info("@AfterAll - executed after all test methods.");
    }

    @BeforeEach
    void initializeBaseClass() {
        log.info("Before each");
    }

    @Test
    @Tag("tag1")
    @Tag("tag2")
    void baseClassTest() {
        log.info("test");
    }

    @Nested
    class FirstContext {

        @BeforeEach
        void initializeFirstNesting() {
            log.info("nested - before each");
        }

        @RepeatedTest(10)
        void firstNestedTest() {
            log.info("nested - test");
        }

        @AfterEach
        void afterFirstContext() {
            log.info("nested - after each");
        }
    }
}
