package com.mitake.camel.fetnp.service;

import com.mitake.camel.fetnp.dao.FetNp412Dao;

import java.util.List;

public class FetNp412Service {

    private FetNp412Dao dao = null;

    public int batchInsert412ReceiverListData(List<String> listData){
        return dao.batchInsert412ReceiverListData(listData);
    }

    public FetNp412Dao getDao() {
        return dao;
    }

    public void setDao(FetNp412Dao dao) {
        this.dao = dao;
    }
}
