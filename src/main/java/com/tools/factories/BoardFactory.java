package com.tools.factories;

import com.tools.models.Board;

public class BoardFactory {
    public static Board getBoardInstance(String name) {
        Board board = new Board();
        board.setName(name);
        return board;
    }
}
