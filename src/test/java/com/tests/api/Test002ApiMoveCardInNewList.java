package com.tests.api;

import net.bytebuddy.utility.RandomString;
import net.serenitybdd.junit.runners.SerenityRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test002ApiMoveCardInNewList extends BaseApiTest {
    private String listName = RandomString.make(10);
    private String cardName = RandomString.make(10);
    private String newListName = RandomString.make(15);

    @Test
    public void test002MoveCardInNewList() {
        listApiSteps.addListInBoard(boardName, listName);
        cardApiSteps.createCard(listName, cardName);

        listApiSteps.addListInBoard(boardName, newListName);
        cardApiSteps.updateCardList(cardName, newListName);
        cardApiSteps.verifyCardIsPresent(cardName);
    }
}
