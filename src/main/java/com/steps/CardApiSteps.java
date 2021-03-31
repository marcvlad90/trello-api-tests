package com.steps;

import java.util.Map;

import net.bytebuddy.utility.RandomString;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Card;
import com.tools.entities.List;
import com.tools.factories.CardFactory;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

public class CardApiSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;
    @Steps
    BoardApiSteps boardApiSteps;

    @Step
    public void createCard() {
        Card cardRequest = CardFactory.getCardInstance(boardApiSteps.getBoardRandomListId());
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", cardRequest.getName());
        bodyParams.put("idList", cardRequest.getBoardListId());

        Card cardResponse = createResource(ApiUrlConstants.CARD_CREATE, bodyParams, Card.class);
        InstanceUtils.mergeObjects(cardRequest, cardResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.LAST_CREATED_CARD, cardRequest);
    }

    @Step
    public void updateCardList() {
        Card cardRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_CARD);
        List list = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_LIST);
        cardRequest.setBoardListId(list.getId());
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("idList", list.getId());
        updateResource(ApiUrlConstants.CARD_GET, bodyParams, cardRequest.getId());

        SerenitySessionUtils.putOnSession(SerenityKeyConstants.LAST_CREATED_CARD, cardRequest);
    }

    @Step
    public void updateCardName() {
        Card cardRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_CARD);
        String newName = RandomString.make(15);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", newName);

        updateResource(ApiUrlConstants.CARD_GET, bodyParams, cardRequest.getId());

        cardRequest.setName(newName);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.LAST_CREATED_CARD, cardRequest);
    }

    @Step
    public void deleteCard() {
        Card cardRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_CARD);

        deleteResource(ApiUrlConstants.CARD_GET, cardRequest.getId());
    }

    public Card getCardFromServer() {
        Card expectedCard = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_CARD);
        return getResource(ApiUrlConstants.CARD_GET, Card.class, expectedCard.getId());
    }

    @Step
    public void verifyCardIsPresent() {
        Assert.assertTrue("Card is not present!", SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_CARD).equals(getCardFromServer()));
    }

    @Step
    public void verifyCardIsNotPresent() {
        Card expectedCard = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LAST_CREATED_CARD);

        Assert.assertTrue("Card is not present!",
                getNotFoundResourceMessage(ApiUrlConstants.CARD_GET, getCommonBodyParams(), expectedCard.getId()).equals(
                        "The requested resource was not found."));
    }
}
