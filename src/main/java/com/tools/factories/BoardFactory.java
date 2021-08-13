package com.tools.factories;

import net.bytebuddy.utility.RandomString;

import com.tools.models.Board;

public class BoardFactory {
    public static Board getBoardInstance() {
        Board board = new Board();
        board.setName(RandomString.make(10));
        return board;
    }
}
