package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.CardApiSteps;
import com.steps.ListApiSteps;

@RunWith(SerenityRunner.class)
public class Test002MoveCardInNewList extends BaseTest {
    @Steps
    private CardApiSteps cardApiSteps;
    @Steps
    private ListApiSteps listApiSteps;

    @Test
    public void test002MoveCardInNewList() {
        cardApiSteps.createCard();
        listApiSteps.createList();

        cardApiSteps.updateCardList();
        cardApiSteps.verifyCardIsPresent();
    }
}
