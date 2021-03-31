package com.tests.api;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.CardApiSteps;
import com.steps.api.ListApiSteps;

@RunWith(SerenityRunner.class)
public class Test002ApiMoveCardInNewList extends BaseTest {
    @Steps
    private CardApiSteps cardApiSteps;
    @Steps
    private ListApiSteps listApiSteps;

    @Test
    public void test002MoveCardInNewList() {
        cardApiSteps.createCard();
        listApiSteps.addListInTheLastCreatedBoard();

        cardApiSteps.updateCardList();
        cardApiSteps.verifyCardIsPresent();
    }
}
