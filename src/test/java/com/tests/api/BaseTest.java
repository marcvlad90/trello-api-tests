package com.tests.api;

import junit.framework.TestCase;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.StepEventBus;

import org.junit.After;
import org.junit.Before;

import com.steps.api.BoardApiSteps;

public class BaseTest extends TestCase {
    @Steps
    private BoardApiSteps boardApiSteps;

    @Before
    public void setup() {
        //        System.setProperty("http.proxyHost", "localhost");
        //        System.setProperty("http.proxyPort", "8080");
        //        System.setProperty("https.proxyHost", "localhost");
        //        System.setProperty("https.proxyPort", "8080");
        System.setProperty("env", "test-env");
        boardApiSteps.createBoard();
    }

    @Override
    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        boardApiSteps.deleteBoard();
    }
}
