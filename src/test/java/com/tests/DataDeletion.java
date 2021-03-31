package com.tests;

import junit.framework.TestCase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.BoardApiSteps;

@RunWith(SerenityRunner.class)
public class DataDeletion extends TestCase {
    @Steps
    private BoardApiSteps boardApiSteps;

    @Before
    public void setup() {
        //        System.setProperty("http.proxyHost", "localhost");
        //        System.setProperty("http.proxyPort", "8080");
        //        System.setProperty("https.proxyHost", "localhost");
        //        System.setProperty("https.proxyPort", "8080");
        System.setProperty("env", "test-env");
    }

    @Test
    public void dataDeletion() {
        boardApiSteps.deleteAllBoards();
    }
}
