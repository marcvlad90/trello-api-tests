package com.tools.factories;

import net.bytebuddy.utility.RandomString;

import com.tools.entities.List;

public class ListFactory {
    public static List getListInstance(String boardId) {
        List list = new List();
        list.setBoardId(boardId);
        list.setName(RandomString.make(10));
        list.setPosition("bottom");
        list.setClosed(false);
        return list;
    }
}
