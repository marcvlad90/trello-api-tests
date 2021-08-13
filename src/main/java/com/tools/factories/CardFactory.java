package com.tools.factories;

import com.tools.models.Card;

public class CardFactory {
    public static Card getCardInstance(String boardListId, String name) {
        Card card = new Card();
        card.setName(name);
        card.setBoardListId(boardListId);
        return card;
    }

}
