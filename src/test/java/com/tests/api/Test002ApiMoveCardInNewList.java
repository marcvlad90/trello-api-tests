package com.tests.api;

import net.serenitybdd.junit.runners.SerenityRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test002ApiMoveCardInNewList extends BaseTest {

    @Test
    public void test002MoveCardInNewList() {
        cardApiSteps.createCard();
        listApiSteps.addListInTheLastCreatedBoard();

        cardApiSteps.updateCardList();
        cardApiSteps.verifyCardIsPresent();
    }
}
