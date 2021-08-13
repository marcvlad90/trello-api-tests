package com.steps.ui;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import com.pages.BoardsPage;
import com.tools.constants.SerenityKeyConstants;
import com.tools.models.Board;
import com.tools.utils.SerenitySessionUtils;

public class BoardsSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    private BoardsPage boardsPage;

    @Step
    public void openTheLastCreatedBoard() {
        Board board = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_BOARD);
        boardsPage.openBoard(board.getName());
    }
}
