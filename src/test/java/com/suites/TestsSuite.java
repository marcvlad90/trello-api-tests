package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.api.DataCreation;
import com.tests.api.DataDeletion;
import com.tests.api.Test001ApiCrudCard;
import com.tests.api.Test002ApiMoveCardInNewList;
import com.tests.api.Test003ApiCrudList;
import com.tests.api.Test004ApiEmailIntegration;

@RunWith(Suite.class)
@SuiteClasses({
    DataCreation.class,
    Test001ApiCrudCard.class,
    Test002ApiMoveCardInNewList.class,
    Test003ApiCrudList.class,
    Test004ApiEmailIntegration.class,
    DataDeletion.class,
})
public class TestsSuite {

}