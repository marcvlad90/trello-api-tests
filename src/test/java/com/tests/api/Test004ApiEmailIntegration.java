package com.tests.api;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.BoardApiSteps;
import com.tools.constants.EnvironmentConstants;

@RunWith(SerenityRunner.class)
public class Test004ApiEmailIntegration extends BaseTest {
    @Steps
    private BoardApiSteps boardApiSteps;

    @Test
    public void emailIntegration() {
        boardApiSteps.inviteMemberToBoardViaEmail(EnvironmentConstants.SECONDARY_USERNAME);
        boardApiSteps.deleteBoard();
        boardApiSteps.verifyBoardMemberRemovalNotification(EnvironmentConstants.SECONDARY_USERNAME, EnvironmentConstants.SECONDARY_USERNAME_PASSWORD);
    }

    @Override
    @After
    public void tearDown() {
    }
}
