package com.tests.api;

import net.bytebuddy.utility.RandomString;
import net.serenitybdd.junit.runners.SerenityRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test001ApiCrudCard extends BaseApiTest {
    private String listName = RandomString.make(10);
    private String cardName = RandomString.make(10);
    private String cardNewName = RandomString.make(15);

    @Test
    public void test001CrudCard() {
        listApiSteps.addListInBoard(boardName, listName);
        cardApiSteps.createCard(listName, cardName);
        cardApiSteps.verifyCardIsPresent(cardName);

        cardApiSteps.updateCardName(cardName, cardNewName);
        cardApiSteps.verifyCardIsPresent(cardNewName);

        cardApiSteps.deleteCard(cardNewName);
        cardApiSteps.verifyCardIsNotPresent(cardNewName);
    }
}
