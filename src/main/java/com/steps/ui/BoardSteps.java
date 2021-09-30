package com.steps.ui;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import org.junit.Assert;

import com.pages.BoardPage;

public class BoardSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    private BoardPage boardPage;

    @Step
    public void createCardInTheList(String cardName, String listName) {
        boardPage.createCardInTheList(cardName, listName);
    }

    @Step
    public void checkThatCardIsPresentInTheList(String cardName, String listName) {
        Assert.assertTrue(String.format("%s card was not found!", cardName), boardPage.isCardDisplayedInTheList(cardName, listName));
    }
}
