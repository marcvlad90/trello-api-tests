package com.steps.ui;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import com.pages.LoginPage;
import com.tools.constants.EnvironmentConstants;

public class LoginSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    private LoginPage loginPage;

    @Step
    public void inputUsername(String username) {
        loginPage.inputUsername(username);
    }

    @Step
    public void inputPassword(String password) {
        loginPage.inputPassword(password);
    }

    @Step
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Step
    public void login() {
        loginPage.open();
        inputUsername(EnvironmentConstants.USERNAME);
        inputPassword(EnvironmentConstants.PASSWORD);
        loginPage.clickLoginButton();
    }
}
