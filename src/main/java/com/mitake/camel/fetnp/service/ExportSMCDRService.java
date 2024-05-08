package com.mitake.camel.fetnp.service;

import com.mitake.camel.fetnp.dao.ExportSMCDR412Dao;
import com.mitake.camel.fetnp.dao.ExportSMCDRDao;

public class ExportSMCDRService {

	private ExportSMCDRDao exportSMCDRDao = null;
	private ExportSMCDR412Dao exportSMCDR412Dao = null;

	public int exportChtSMCDR(String fileName) {
		return (int) exportSMCDRDao.exportChtSMCDRToFile(fileName);
	}

	public int exportFetSMCDR(String fileName) {
		return (int) exportSMCDRDao.exportFetSMCDRToFile(fileName);
	}

	public int exportTwmSMCDR(String fileName) {
		return (int) exportSMCDRDao.exportTWMSMCDRToFile(fileName);
	}

	public int exportTwsmsSMCDR(String fileName) {
		return (int) exportSMCDRDao.exportTWSMSSMCDRToFile(fileName);
	}

	public int exportCcentSMCDR(String fileName) {
		return (int) exportSMCDRDao.exportCCENTSMCDRToFile(fileName);
	}

	public int export412ChtSMCDR(String fileName) {
		return (int) exportSMCDR412Dao.export412ChtSMCDRToFile(fileName);
	}

	public int export412FetSMCDR(String fileName) {
		return (int) exportSMCDR412Dao.export412FetSMCDRToFile(fileName);
	}

	public int export412TwmSMCDR(String fileName) {
		return (int) exportSMCDR412Dao.export412TWMSMCDRToFile(fileName);
	}


	public ExportSMCDRDao getExportSMCDRDao() {
		return exportSMCDRDao;
	}

	public void setExportSMCDRDao(ExportSMCDRDao exportSMCDRDao) {
		this.exportSMCDRDao = exportSMCDRDao;
	}

	public ExportSMCDR412Dao getExportSMCDR412Dao() {
		return exportSMCDR412Dao;
	}

	public void setExportSMCDR412Dao(ExportSMCDR412Dao exportSMCDR412Dao) {
		this.exportSMCDR412Dao = exportSMCDR412Dao;
	}
}
