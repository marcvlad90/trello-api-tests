package com.tests.api;

import net.serenitybdd.junit.runners.SerenityRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test003ApiCrudList extends BaseTest {

    @Test
    public void test003CrudList() {
        listApiSteps.addListInTheLastCreatedBoard();
        listApiSteps.verifyListIsPresent();

        listApiSteps.updateListName();
        listApiSteps.verifyListIsPresent();

        listApiSteps.archiveList();
        listApiSteps.verifyListIsArchived();
    }
}
