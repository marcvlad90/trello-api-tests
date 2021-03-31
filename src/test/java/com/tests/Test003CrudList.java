package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.ListApiSteps;

@RunWith(SerenityRunner.class)
public class Test003CrudList extends BaseTest {
    @Steps
    private ListApiSteps listApiSteps;

    @Test
    public void test003CrudList() {
        listApiSteps.createList();
        listApiSteps.verifyListIsPresent();

        listApiSteps.updateListName();
        listApiSteps.verifyListIsPresent();

        listApiSteps.archiveList();
        listApiSteps.verifyListIsArchived();
    }
}
