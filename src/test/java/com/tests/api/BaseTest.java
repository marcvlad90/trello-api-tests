package com.tests.api;

import junit.framework.TestCase;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.StepEventBus;

import org.junit.After;
import org.junit.Before;

import com.steps.api.BoardApiSteps;
import com.steps.api.CardApiSteps;
import com.steps.api.ListApiSteps;

public class BaseTest extends TestCase {
    @Steps
    protected BoardApiSteps boardApiSteps;
    @Steps
    protected CardApiSteps cardApiSteps;
    @Steps
    protected ListApiSteps listApiSteps;

    @Before
    public void setup() {
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
