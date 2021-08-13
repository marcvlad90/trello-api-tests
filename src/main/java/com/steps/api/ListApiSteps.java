package com.steps.api;

import java.util.Map;

import net.bytebuddy.utility.RandomString;
import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.factories.ListFactory;
import com.tools.models.Board;
import com.tools.models.List;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

public class ListApiSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void addListInTheLastCreatedBoard() {
        Board board = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_BOARD);
        List listRequest = ListFactory.getListInstance(board.getId());
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", listRequest.getName());
        bodyParams.put("pos", listRequest.getPosition());
        bodyParams.put("idBoard", board.getId());
        List listResponse = createResource(ApiUrlConstants.LIST_CREATE, bodyParams, List.class);

        InstanceUtils.mergeObjects(listRequest, listResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.LAST_CREATED_LIST, listRequest);
    }

    @Step
    public void updateListName() {
        List listRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_LIST);
        String newName = RandomString.make(15);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", newName);

        updateResource(ApiUrlConstants.LIST_GET, bodyParams, listRequest.getId());

        listRequest.setName(newName);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.LAST_CREATED_LIST, listRequest);
    }

    @Step
    public void archiveList() {
        List listRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_LIST);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("closed", "true");

        updateResource(ApiUrlConstants.LIST_GET, bodyParams, listRequest.getId());

        listRequest.setClosed(true);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.LAST_CREATED_LIST, listRequest);
    }

    @Step
    public void verifyListIsArchived() {
        Assert.assertTrue("List is not archived!", getListFromServer().isClosed());
    }

    public List getListFromServer() {
        List expectedList = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_LIST);
        return getResource(ApiUrlConstants.LIST_GET, List.class, expectedList.getId());
    }

    @Step
    public void verifyListIsPresent() {
        Assert.assertTrue("List is not present!", SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_LIST).equals(getListFromServer()));
    }
}
