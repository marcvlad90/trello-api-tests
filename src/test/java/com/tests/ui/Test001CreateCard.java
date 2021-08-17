package com.tests.ui;

import net.bytebuddy.utility.RandomString;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.StepEventBus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.BoardApiSteps;
import com.steps.api.ListApiSteps;
import com.steps.ui.BoardSteps;
import com.steps.ui.BoardsSteps;
import com.steps.ui.LoginSteps;

@RunWith(SerenityRunner.class)
public class Test001CreateCard extends BaseTest {

    @Steps
    private BoardApiSteps boardApiSteps;
    @Steps
    private ListApiSteps listApiSteps;
    @Steps
    private LoginSteps loginSteps;
    @Steps
    private BoardsSteps boardsSteps;
    @Steps
    private BoardSteps boardSteps;
    private String firstListName = RandomString.make(10);
    private String secondListName = RandomString.make(10);
    private String cardName = RandomString.make(10);

    @Before
    public void dataPrep() {
        boardApiSteps.createBoard(boardName);
        listApiSteps.addListInBoard(boardName, firstListName);
        listApiSteps.addListInBoard(boardName, secondListName);
        loginSteps.login();
        boardsSteps.openBoard(boardName);
    }

    @Test
    public void test001CreateCard() {
        boardSteps.addCardInTheList(firstListName, cardName);
        boardSteps.addCardInTheList(secondListName, cardName);
        boardSteps.checkThatCardIsPresentInTheList(firstListName, cardName);
        boardSteps.checkThatCardIsPresentInTheList(secondListName, cardName);
    }

    @Override
    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        boardApiSteps.deleteBoard(boardName);
    }
}
