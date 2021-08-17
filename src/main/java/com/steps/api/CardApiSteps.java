package com.steps.api;

import java.util.Map;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.dao.card.CardDao;
import com.dao.list.ListDao;
import com.google.inject.Inject;
import com.tools.constants.ApiUrlConstants;
import com.tools.factories.CardFactory;
import com.tools.models.Card;
import com.tools.models.List;
import com.tools.utils.InstanceUtils;

public class CardApiSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;
    @Steps
    private BoardApiSteps boardApiSteps;
    @Inject
    ListDao listDao;
    @Inject
    CardDao cardDao;

    @Step
    public void createCard(String listName, String cardName) {
        List list = listDao.getListByName(listName);
        Card cardRequest = CardFactory.getCardInstance(list.getId(), cardName);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", cardRequest.getName());
        bodyParams.put("idList", cardRequest.getBoardListId());
        Card cardResponse = createResource(ApiUrlConstants.CARD_CREATE, bodyParams, Card.class);

        InstanceUtils.mergeObjects(cardRequest, cardResponse);
        cardDao.saveCard(cardRequest);
    }

    @Step
    public void updateCardList(String cardName, String newListName) {
        Card cardRequest = cardDao.getCardByName(cardName);
        List list = listDao.getListByName(newListName);
        cardRequest.setBoardListId(list.getId());
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("idList", list.getId());
        updateResource(ApiUrlConstants.CARD_GET, bodyParams, cardRequest.getId());

        cardDao.updateCard(cardName, cardRequest);
    }

    @Step
    public void updateCardName(String name, String newName) {
        Card cardRequest = cardDao.getCardByName(name);
        Map<String, String> bodyParams = getCommonBodyParams();
        bodyParams.put("name", newName);

        updateResource(ApiUrlConstants.CARD_GET, bodyParams, cardRequest.getId());

        cardRequest.setName(newName);
        cardDao.updateCard(newName, cardRequest);
    }

    @Step
    public void deleteCard(String name) {
        Card cardRequest = cardDao.getCardByName(name);
        deleteResource(ApiUrlConstants.CARD_GET, cardRequest.getId());
    }

    public Card getCardFromServer(String name) {
        Card expectedCard = cardDao.getCardByName(name);
        return getResource(ApiUrlConstants.CARD_GET, Card.class, expectedCard.getId());
    }

    @Step
    public void verifyCardIsPresent(String name) {
        Assert.assertTrue("Card is not present!", cardDao.getCardByName(name).equals(getCardFromServer(name)));
    }

    @Step
    public void verifyCardIsNotPresent(String name) {
        Card expectedCard = cardDao.getCardByName(name);

        Assert.assertTrue("Card is not present!",
                getNotFoundResourceMessage(ApiUrlConstants.CARD_GET, getCommonBodyParams(), expectedCard.getId()).equals(
                        "The requested resource was not found."));
    }
}
