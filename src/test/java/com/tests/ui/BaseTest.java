package com.tests.ui;

import junit.framework.TestCase;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.StepEventBus;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.steps.api.BoardApiSteps;

public class BaseTest extends TestCase {
    @Managed(uniqueSession = true)
    WebDriver webdriver;
    @Steps
    private BoardApiSteps boardApiSteps;

    @Before
    public void setup() {
        //        System.setProperty("https.proxyHost", "localhost");
        //        System.setProperty("https.proxyPort", "8080");
        System.setProperty("env", "test-env");
    }

    @Override
    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        boardApiSteps.deleteBoard();
    }
}
