package com.steps.api;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.dao.board.BoardAbstractDao;
import com.dao.list.ListAbstractDao;
import com.google.inject.Inject;
import com.tools.constants.ApiUrlConstants;
import com.tools.factories.ListFactory;
import com.tools.models.Board;
import com.tools.models.List;
import com.tools.utils.InstanceUtils;

public class ListApiSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;
    @Inject
    private BoardAbstractDao boardDao;
    @Inject
    public ListAbstractDao listDao;

    @Step
    public void createList(String boardName, String listName) {
        Board board = boardDao.getBoardByName(boardName);
        List listRequest = ListFactory.getListInstance(board.getId(), listName);

        List listResponse = createResource(ApiUrlConstants.LIST_CREATE, listRequest, List.class);

        InstanceUtils.mergeObjects(listRequest, listResponse);
        listDao.saveList(listRequest);
    }

    @Step
    public void updateListName(String name, String newName) {
        List listRequest = listDao.getListByName(name);
        listRequest.setName(newName);

        updateResource(ApiUrlConstants.LIST_GET, listRequest, listRequest.getId());

        listDao.updateList(newName, listRequest);
    }

    @Step
    public void archiveList(String name) {
        List listRequest = listDao.getListByName(name);
        listRequest.setClosed(true);

        updateResource(ApiUrlConstants.LIST_GET, listRequest, listRequest.getId());

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
    public void createListInBoard(String boardName, String listName) {
        Board board = boardDao.getBoardByName(boardName);
        List listRequest = ListFactory.getListInstance(board.getId(), listName);

        List listResponse = createResource(ApiUrlConstants.LIST_CREATE, listRequest, List.class);

        InstanceUtils.mergeObjects(listRequest, listResponse);
        listDao.saveList(listRequest);
    }
}
