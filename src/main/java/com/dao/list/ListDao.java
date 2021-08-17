package com.dao.list;

import java.lang.reflect.InvocationTargetException;

import com.tools.constants.SerenityKeyConstants;
import com.tools.models.List;
import com.tools.utils.SerenitySessionUtils;

public class ListDao implements ListAbstractDao {

    @Override
    public void saveList(List list) {
        System.out.println("Saving list " + list.getName() + " ...");
        SerenitySessionUtils.saveObjectInSerenitySessionList(SerenityKeyConstants.LIST, list);

    }

    @Override
    public List getListByName(String name) {
        return getAllLists().parallelStream().filter(item -> item.getName().contentEquals(name)).findFirst().orElse(null);
    }

    @Override
    public java.util.List<List> getAllLists() {
        return SerenitySessionUtils.getFromSession(SerenityKeyConstants.LIST);
    }

    @Override
    public void updateList(String name, List newList) {
        try {
            SerenitySessionUtils.replaceObjectInSerenitySessionList(SerenityKeyConstants.LIST, newList,
                    "name", name);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
