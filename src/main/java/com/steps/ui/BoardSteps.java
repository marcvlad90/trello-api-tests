package com.steps.ui;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import org.junit.Assert;

import com.pages.BoardPage;
import com.tools.constants.SerenityKeyConstants;
import com.tools.models.List;
import com.tools.utils.SerenitySessionUtils;

public class BoardSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    private BoardPage boardPage;

    @Step
    public void addCardInTheLastCreatedList(String cardName) {
        List list = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_LIST);
        boardPage.createCardOnTheList(list.getName(), cardName);
    }

    @Step
    public void checkThatCardIsPresentInTheList(String cardName) {
        List list = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_LIST);
        Assert.assertTrue(String.format("%s card was not found!", cardName), boardPage.getCardContainer(list.getName(), cardName).isDisplayed());
    }
}
