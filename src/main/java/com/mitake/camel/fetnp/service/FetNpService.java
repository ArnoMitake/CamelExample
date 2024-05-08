package com.mitake.camel.fetnp.service;

import com.mitake.camel.fetnp.dao.FetNpDao;

import java.util.List;

public class FetNpService {

    private FetNpDao dao = null;

    public int batchInsertReceiverListData(List<String> listData){
        return dao.batchInsertReceiverListData(listData);
    }

    public FetNpDao getDao() {
        return dao;
    }

    public void setDao(FetNpDao dao) {
        this.dao = dao;
    }
}
