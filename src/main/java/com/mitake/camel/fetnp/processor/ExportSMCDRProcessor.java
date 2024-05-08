package com.mitake.camel.fetnp.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.mitake.camel.fetnp.service.ExportSMCDRService;

public class ExportSMCDRProcessor {
	private final static Logger logger = LoggerFactory.getLogger(ExportSMCDRProcessor.class);

	private String filePath = null;
	private String exportTelecomStr = null;

	private ExportSMCDRService service = null;

	public void exportSMCDRDestNoList(Exchange ech) throws Exception {

		int exportCht = -1;
		int exportFet = -1;
		int exportTwm = -1;
		int exportTwsms = -1;
		int exportCcent = -1;

		StopWatch sw = new StopWatch("exportSMCDRDestNoList");

		if (StringUtils.contains(exportTelecomStr, "CHT")) {
			sw.start("CHT");
			exportCht = service.exportChtSMCDR(filePath + "/DstnoList_CHT_"
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
			sw.stop();
			logger.info("匯出CHT DstNo共 {} 筆，耗時:{} ms", exportCht, sw.getLastTaskTimeMillis());
		}

		if (StringUtils.contains(exportTelecomStr, "FET")) {
			sw.start("FET");
			exportFet = service.exportFetSMCDR(filePath + "/DstnoList_FET_"
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
			sw.stop();
			logger.info("匯出FET DstNo共 {} 筆，耗時:{} ms", exportFet, sw.getLastTaskTimeMillis());
		}

		if (StringUtils.contains(exportTelecomStr, "TWM")) {
			sw.start("TWM");
			exportTwm = service.exportTwmSMCDR(filePath + "/DstnoList_TWM_"
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
			sw.stop();
			logger.info("匯出TWM DstNo共 {} 筆，耗時:{} ms", exportTwm, sw.getLastTaskTimeMillis());
		}

		if (StringUtils.contains(exportTelecomStr, "TWSMS")) {
			sw.start("TWSMS");
			exportTwsms = service.exportTwsmsSMCDR(filePath + "/DstnoList_TWSMS_"
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
			sw.stop();
			logger.info("匯出TWSMS DstNo共 {} 筆，耗時:{} ms", exportTwsms, sw.getLastTaskTimeMillis());
		}

		if (StringUtils.contains(exportTelecomStr, "CCENT")) {
			sw.start("CCENT");
			exportCcent = service.exportCcentSMCDR(filePath + "/DstnoList_CCENT_"
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
			sw.stop();
			logger.info("匯出CCENT DstNo共 {} 筆，耗時:{} ms", exportCcent, sw.getLastTaskTimeMillis());
		}

		if (StringUtils.contains(exportTelecomStr, "412CHT")) {
			sw.start("412CHT");
			exportCht = service.export412ChtSMCDR(filePath + "/DstnoList_412CHT_"
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
			sw.stop();
			logger.info("匯出412CHT DstNo共 {} 筆，耗時:{} ms", exportCht, sw.getLastTaskTimeMillis());
		}

		if (StringUtils.contains(exportTelecomStr, "412FET")) {
			sw.start("412FET");
			exportFet = service.export412FetSMCDR(filePath + "/DstnoList_412FET_"
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
			sw.stop();
			logger.info("匯出412FET DstNo共 {} 筆，耗時:{} ms", exportFet, sw.getLastTaskTimeMillis());
		}

		if (StringUtils.contains(exportTelecomStr, "412TWM")) {
			sw.start("412TWM");
			exportTwm = service.export412TwmSMCDR(filePath + "/DstnoList_412TWM_"
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
			sw.stop();
			logger.info("匯出412TWM DstNo共 {} 筆，耗時:{} ms", exportTwm, sw.getLastTaskTimeMillis());
		}

		logger.info(sw.toString());

	}

	public ExportSMCDRService getService() {
		return service;
	}

	public void setService(ExportSMCDRService service) {
		this.service = service;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExportTelecomStr() {
		return exportTelecomStr;
	}

	public void setExportTelecomStr(String exportTelecomStr) {
		this.exportTelecomStr = exportTelecomStr;
	}

}
