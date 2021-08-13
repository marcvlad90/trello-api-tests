package com.steps.api;

import java.util.Map;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.dao.board.BoardDao;
import com.google.inject.Inject;
import com.tools.constants.ApiUrlConstants;
import com.tools.email.EmailProcessor;
import com.tools.email.GmailService;
import com.tools.factories.BoardFactory;
import com.tools.models.Board;
import com.tools.models.Mail;
import com.tools.utils.InstanceUtils;

public class BoardApiSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;
    @Inject
    public BoardDao boardDao;

    @Step
    public void inviteMemberToBoard(String boardName, String email) {
        Board board = boardDao.getBoardByName(boardName);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("email", email);
        updateResource(ApiUrlConstants.BOARD_MEMBER_INVITATION, bodyParams, board.getId());
    }

    @Step
    public void verifyBoardMemberRemovalNotification(String boardName, String email, String password) {
        EmailProcessor emailProcessor = new EmailProcessor(new GmailService());
        int numberOfTries = 0;
        boolean isFound = false;
        while (numberOfTries != 30 && !isFound) {
            numberOfTries++;
            Mail mail = emailProcessor.getTheLastEmailWithSubject(email, password, "removed you from the board " + boardName);
            if (mail != null) {
                System.out.println("Email found");
                isFound = true;
                break;
            }
            waitABit(5000);
        }
        Assert.assertTrue(isFound);
    }

    @Step
    public void createBoard(String name) {
        Board boardRequest = BoardFactory.getBoardInstance(name);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", boardRequest.getName());
        Board boardResponse = createResource(ApiUrlConstants.BOARD_CREATE, bodyParams, Board.class);

        InstanceUtils.mergeObjects(boardRequest, boardResponse);
        boardDao.saveBoard(boardRequest);
    }

    @Step
    public void deleteBoard(String name) {
        Board board = boardDao.getBoardByName(name);
        deleteResource(ApiUrlConstants.BOARD_GET, board.getId());
        boardDao.removeBoard(board);
    }

    @Step
    public void deleteAllBoards() {
        Board[] boards = getResource(ApiUrlConstants.BOARDS_GET_ALL, Board[].class);
        for (Board board : boards) {
            deleteResource(ApiUrlConstants.BOARD_GET, board.getId());
        }
    }

    //    public String getBoardRandomListId(String boardName) {
    //        Board board = boardDao.getBoardByName(boardName);
    //        BoardsList[] boardList = getResource(ApiUrlConstants.BOARDS_LISTS, BoardsList[].class, board.getId());
    //        Random rand = new Random();
    //        return boardList[rand.nextInt(boardList.length)].getId();
    //    }
}
