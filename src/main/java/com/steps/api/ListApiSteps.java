package com.steps.api;

import java.util.Map;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.dao.board.BoardDao;
import com.dao.list.ListDao;
import com.google.inject.Inject;
import com.tools.constants.ApiUrlConstants;
import com.tools.factories.ListFactory;
import com.tools.models.Board;
import com.tools.models.List;
import com.tools.utils.InstanceUtils;

public class ListApiSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;
    @Inject
    private BoardDao boardDao;
    @Inject
    public ListDao listDao;

    @Step
    public void createList(String boardName, String listName) {
        Board board = boardDao.getBoardByName(boardName);
        List listRequest = ListFactory.getListInstance(board.getId(), listName);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", listRequest.getName());
        bodyParams.put("pos", listRequest.getPosition());
        bodyParams.put("idBoard", board.getId());
        List listResponse = createResource(ApiUrlConstants.LIST_CREATE, bodyParams, List.class);

        InstanceUtils.mergeObjects(listRequest, listResponse);
        listDao.saveList(listRequest);
    }

    @Step
    public void updateListName(String name, String newName) {
        List listRequest = listDao.getListByName(name);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", newName);

        updateResource(ApiUrlConstants.LIST_GET, bodyParams, listRequest.getId());

        listRequest.setName(newName);
        listDao.updateList(newName, listRequest);
    }

    @Step
    public void archiveList(String name) {
        List listRequest = listDao.getListByName(name);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("closed", "true");

        updateResource(ApiUrlConstants.LIST_GET, bodyParams, listRequest.getId());

        listRequest.setClosed(true);
        listDao.updateList(name, listRequest);
    }

    @Step
    public void verifyListIsArchived(String name) {
        Assert.assertTrue("List is not archived!", getListFromServer(name).isClosed());
    }

    public List getListFromServer(String name) {
        List expectedList = listDao.getListByName(name);
        return getResource(ApiUrlConstants.LIST_GET, List.class, expectedList.getId());
    }

    @Step
    public void verifyListIsPresent(String name) {
        Assert.assertTrue("List is not present!", listDao.getListByName(name).equals(getListFromServer(name)));
    }

    @Step
    public void addListInBoard(String boardName, String listName) {
        Board board = boardDao.getBoardByName(boardName);
        List listRequest = ListFactory.getListInstance(board.getId(), listName);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", listRequest.getName());
        bodyParams.put("pos", listRequest.getPosition());
        bodyParams.put("idBoard", board.getId());
        List listResponse = createResource(ApiUrlConstants.LIST_CREATE, bodyParams, List.class);

        InstanceUtils.mergeObjects(listRequest, listResponse);
        listDao.saveList(listRequest);
    }
}
