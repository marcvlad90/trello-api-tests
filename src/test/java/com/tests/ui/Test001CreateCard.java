package com.tests.ui;

import net.bytebuddy.utility.RandomString;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.steps.StepEventBus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test001CreateCard extends BaseTest {
    private String firstListName = RandomString.make(10);
    private String cardName = RandomString.make(10);

    @Before
    public void dataPrep() {
        boardApiSteps.createBoard(boardName);
        listApiSteps.addListInBoard(boardName, firstListName);
        loginSteps.login();
        boardsSteps.openBoard(boardName);
    }

    @Test
    public void test001CreateCard() {
        boardSteps.createCardInTheList(firstListName, cardName);
        boardSteps.checkThatCardIsPresentInTheList(firstListName, cardName);
    }

    @Override
    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        boardApiSteps.deleteBoard(boardName);
    }
}
