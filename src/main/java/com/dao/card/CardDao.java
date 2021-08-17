package com.dao.card;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.tools.constants.SerenityKeyConstants;
import com.tools.models.Card;
import com.tools.utils.SerenitySessionUtils;

public class CardDao implements CardAbstractDao {

    @Override
    public void saveCard(Card card) {
        System.out.println("Saving card " + card.getName() + " ...");
        SerenitySessionUtils.saveObjectInSerenitySessionList(SerenityKeyConstants.CARD, card);

    }

    @Override
    public Card getCardByName(String name) {
        return getAllCards().parallelStream().filter(item -> item.getName().contentEquals(name)).findFirst().orElse(null);
    }

    @Override
    public void updateCard(String name, Card newCard) {
        try {
            SerenitySessionUtils.replaceObjectInSerenitySessionList(SerenityKeyConstants.CARD, newCard,
                    "name", name);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Card> getAllCards() {
        return SerenitySessionUtils.getFromSession(SerenityKeyConstants.CARD);
    }

}
