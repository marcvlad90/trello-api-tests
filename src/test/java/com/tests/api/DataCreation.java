package com.tests.api;

import java.io.IOException;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.BoardApiSteps;

@RunWith(SerenityRunner.class)
public class DataCreation {
    @Steps
    private BoardApiSteps boardApiSteps;

    @Before
    public void setup() {
        //        System.setProperty("https.proxyHost", "localhost");
        //        System.setProperty("https.proxyPort", "8080");
        System.setProperty("env", "test-env");
    }

    @Test
    public void dataCreation() throws IOException {
        boardApiSteps.createBoardsWithCards(5);
    }
}
