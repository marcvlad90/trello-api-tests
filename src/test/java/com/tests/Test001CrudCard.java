package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.CardApiSteps;

@RunWith(SerenityRunner.class)
public class Test001CrudCard extends BaseTest {
    @Steps
    private CardApiSteps cardApiSteps;

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
