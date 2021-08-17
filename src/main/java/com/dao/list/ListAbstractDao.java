package com.dao.list;

import com.google.inject.ImplementedBy;
import com.tools.models.List;

@ImplementedBy(ListDao.class)
public interface ListAbstractDao {
    public void saveList(List list);

    public List getListByName(String name);

    public java.util.List<List> getAllLists();

    public void updateList(String name, List list);
}
