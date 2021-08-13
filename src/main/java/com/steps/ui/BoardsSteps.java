package com.steps.ui;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import com.pages.BoardsPage;

public class BoardsSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    private BoardsPage boardsPage;

    @Step
    public void openBoard(String name) {
        boardsPage.openBoard(name);
    }
}
