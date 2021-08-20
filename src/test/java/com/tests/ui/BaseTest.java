package com.tests.ui;

import junit.framework.TestCase;
import net.bytebuddy.utility.RandomString;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.StepEventBus;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.steps.api.BoardApiSteps;
import com.steps.api.ListApiSteps;
import com.steps.ui.BoardSteps;
import com.steps.ui.BoardsSteps;
import com.steps.ui.LoginSteps;

public class BaseTest extends TestCase {

    @Managed(uniqueSession = true)
    protected WebDriver webdriver;
    @Steps
    protected BoardApiSteps boardApiSteps;
    @Steps
    protected ListApiSteps listApiSteps;
    @Steps
    protected LoginSteps loginSteps;
    @Steps
    protected BoardsSteps boardsSteps;
    @Steps
    protected BoardSteps boardSteps;
    protected String boardName = RandomString.make(10);

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
        boardApiSteps.deleteBoard(boardName);
    }
}
