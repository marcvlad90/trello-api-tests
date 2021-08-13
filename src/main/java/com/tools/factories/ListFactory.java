package com.tools.factories;

import com.tools.models.List;

public class ListFactory {
    public static List getListInstance(String boardId, String name) {
        List list = new List();
        list.setBoardId(boardId);
        list.setName(name);
        list.setPosition("bottom");
        list.setClosed(false);
        return list;
    }
}
