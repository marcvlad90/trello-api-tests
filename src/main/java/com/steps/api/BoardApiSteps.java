package com.steps.api;

import static net.thucydides.core.steps.stepdata.StepData.withTestDataFrom;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.Constants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.email.EmailProcessor;
import com.tools.email.GmailService;
import com.tools.entities.Board;
import com.tools.entities.BoardList;
import com.tools.entities.Card;
import com.tools.entities.Mail;
import com.tools.factories.BoardFactory;
import com.tools.factories.CardFactory;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

public class BoardApiSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void inviteMemberToBoardViaEmail(String email) {
        Board board = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_BOARD);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("email", email);
        updateResource(ApiUrlConstants.BOARD_MEMBER_INVITATION, bodyParams, board.getId());
    }

    @Step
    public void verifyBoardMemberRemovalNotification(String email, String password) {
        Board board = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_BOARD);
        EmailProcessor emailProcessor = new EmailProcessor(new GmailService());
        int numberOfTries = 0;
        boolean isFound = false;
        while (numberOfTries != 30 && !isFound) {
            numberOfTries++;
            Mail mail = emailProcessor.getTheLastEmailWithSubject(email, password, "removed you from the board " + board.getName());
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
    public void createBoard() {
        Board boardRequest = BoardFactory.getBoardInstance();
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", boardRequest.getName());
        Board boardResponse = createResource(ApiUrlConstants.BOARD_CREATE, bodyParams, Board.class);

        InstanceUtils.mergeObjects(boardRequest, boardResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.LAST_CREATED_BOARD, boardRequest);
    }

    @Step
    public void deleteBoard() {
        Board board = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_BOARD);
        deleteResource(ApiUrlConstants.BOARD_GET, board.getId());
    }

    @Step
    public void deleteAllBoards() {
        Board[] boards = getResource(ApiUrlConstants.BOARDS_GET_ALL, Board[].class);
        Arrays.stream(boards).parallel().forEach(board -> deleteResource(ApiUrlConstants.BOARD_GET, board.getId()));
    }

    public String getBoardRandomListId() {
        Board board = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_BOARD);
        BoardList[] boardList = getResource(ApiUrlConstants.BOARD_LISTS, BoardList[].class, board.getId());
        Random rand = new Random();
        return boardList[rand.nextInt(boardList.length)].getId();
    }

    public String cardName;

    @Step
    public void populateBoardWithCardsFromCsv() {
        String boardListId = getBoardRandomListId();
        Card cardRequest = CardFactory.getCardInstance(boardListId, cardName);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", cardRequest.getName());
        bodyParams.put("idList", cardRequest.getBoardListId());
        createResource(ApiUrlConstants.CARD_CREATE, bodyParams, Card.class);
    }

    @Step
    public void createBoardsWithCards(int numberOfBoards) {
        IntStream.range(0, numberOfBoards).parallel().forEach(i -> {
            createBoard();
            try {
                withTestDataFrom(Constants.TEST_DATA_FILE_PATH_FOR_CSV + "multipleCards.csv")
                .run(this).populateBoardWithCardsFromCsv();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
