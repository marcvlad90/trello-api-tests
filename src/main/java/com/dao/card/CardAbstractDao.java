package com.dao.card;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.tools.models.Card;

@ImplementedBy(CardDao.class)
public interface CardAbstractDao {
    public void saveCard(Card card);

    public List<Card> getAllCards();

    public Card getCardByName(String name);

    public void updateCard(String name, Card newCard);

}
