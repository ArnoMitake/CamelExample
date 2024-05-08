package com.mitake.camel.fetnp.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.mitake.camel.fetnp.service.IODstNoService;

public class IODstNoProcessor {
	private final static Logger logger = LoggerFactory.getLogger(IODstNoProcessor.class);

	private String filePath = null;
	private IODstNoService service = null;

	public void importProcess(Exchange ech) throws Exception {
		Set<String> listData = null;
		StopWatch sw = new StopWatch("ImportDstNo");
		int result = 0;
		listData = ech.getIn().getBody(Set.class);

		if (listData != null) {
			sw.start();
			result = service.batchInsertDstNo(listData);
			sw.stop();
		}
		if (result > 0) {
			logger.info("CDRDeliveryDestNoList_New，DstNo {} 筆資料，耗時:{} ms", result, sw.getTotalTimeMillis());
		}

	}

	public void outPutProcess(Exchange ech) throws Exception {
		StopWatch sw = new StopWatch("OutPutDstNo");
		int result = 0;

		sw.start();
		result = service.outPutDstNo(filePath + "/DstnoList_"
				+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
		sw.stop();

		if (result > 0){
			logger.info("CDRDeliveryDestNoList_New，DstNos {} 筆資料，耗時:{} ms", result, sw.getTotalTimeMillis());
		}

	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public IODstNoService getService() {
		return service;
	}

	public void setService(IODstNoService service) {
		this.service = service;
	}

}
