package com.tests.api;

import net.serenitybdd.junit.runners.SerenityRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test001ApiCrudCard extends BaseTest {

    @Test
    public void test001CrudCard() {
        cardApiSteps.createCard();
        cardApiSteps.verifyCardIsPresent();

        cardApiSteps.updateCardName();
        cardApiSteps.verifyCardIsPresent();

        cardApiSteps.deleteCard();
        cardApiSteps.verifyCardIsNotPresent();
    }
}
