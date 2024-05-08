package com.mitake.camel.fetnp.service;

import java.util.Set;

import com.mitake.camel.fetnp.dao.IODstNoDao;

public class IODstNoService {
	
	private IODstNoDao dao = null;

	public int batchInsertDstNo(Set<String> listData) {
		return dao.batchInsertDstNo(listData);
	}

	public int outPutDstNo(String fileName) {
		return (int) dao.outPutDstNo(fileName);
	}

	public IODstNoDao getDao() {
		return dao;
	}

	public void setDao(IODstNoDao dao) {
		this.dao = dao;
	}

}
