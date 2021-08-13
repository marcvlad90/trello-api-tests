package com.tools.factories;

import net.bytebuddy.utility.RandomString;

import com.tools.models.Card;

public class CardFactory {
    public static Card getCardInstance(String boardListId) {
        Card card = new Card();
        card.setName(RandomString.make(10));
        card.setBoardListId(boardListId);
        return card;
    }

    public static Card getCardInstance(String boardListId, String name) {
        Card card = new Card();
        card.setName(name);
        card.setBoardListId(boardListId);
        return card;
    }
}
