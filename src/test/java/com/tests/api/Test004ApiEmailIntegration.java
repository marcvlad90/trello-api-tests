package com.tests.api;

import net.serenitybdd.junit.runners.SerenityRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tools.constants.EnvironmentConstants;

@RunWith(SerenityRunner.class)
public class Test004ApiEmailIntegration extends BaseApiTest {
    @Test
    public void test004ApiEmailIntegration() {
        boardApiSteps.inviteMemberToBoard(boardName, EnvironmentConstants.SECONDARY_USERNAME);
        boardApiSteps.deleteBoard(boardName);
        boardApiSteps
        .verifyBoardMemberRemovalNotification(boardName, EnvironmentConstants.SECONDARY_USERNAME, EnvironmentConstants.SECONDARY_USERNAME_PASSWORD);
    }
}
