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
    private String listName = RandomString.make(10);
    private String cardName = RandomString.make(10);

    @Before
    public void dataPrep() {
        boardApiSteps.createBoard(boardName);
        listApiSteps.createListInBoard(boardName, listName);
    }

    @Test
    public void test001CreateCard() {
        loginSteps.login();
        boardsSteps.openBoard(boardName);
        boardSteps.createCardInTheList(cardName, listName);
        boardSteps.checkThatCardIsPresentInTheList(cardName, listName);
    }

    @Override
    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        boardApiSteps.deleteBoard(boardName);
    }
}
