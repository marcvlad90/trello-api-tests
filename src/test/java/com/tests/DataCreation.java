package com.tests;

import java.io.IOException;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.BoardApiSteps;

@RunWith(SerenityRunner.class)
public class DataCreation extends BaseTest {
    @Steps
    private BoardApiSteps boardApiSteps;

    @Override
    @Before
    public void setup() {
        //        System.setProperty("http.proxyHost", "localhost");
        //        System.setProperty("http.proxyPort", "8080");
        //        System.setProperty("https.proxyHost", "localhost");
        //        System.setProperty("https.proxyPort", "8080");
        System.setProperty("env", "test-env");
    }

    @Test
    public void dataCreation() throws IOException {
        boardApiSteps.createBoardsWithCards(2);
    }

    @Override
    @After
    public void tearDown() {
    }
}
