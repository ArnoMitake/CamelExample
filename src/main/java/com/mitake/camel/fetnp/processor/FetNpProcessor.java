package com.mitake.camel.fetnp.processor;

import com.mitake.camel.fetnp.service.FetNpService;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class FetNpProcessor {
    protected static final Logger logger = LoggerFactory.getLogger(FetNpProcessor.class);
    private FetNpService fetNpService;

    public void batchInsertReceiverListData(Exchange ech) throws Exception {
        List<String> listData = null;
        StopWatch sw = new StopWatch("batchInsertReceiverListData");
        int result = 0;
        listData = ech.getIn().getBody(ArrayList.class);

        if (listData != null) {
            sw.start();
            result = fetNpService.batchInsertReceiverListData(listData);
            sw.stop();
        }
        if (result > 0) {
            logger.info("匯入WaitToUpdateNP，DstNo {} 筆資料，耗時:{} ms", result, sw.getTotalTimeMillis());
        }
    }


    public FetNpService getFetNpService() {
        return fetNpService;
    }

    public void setFetNpService(FetNpService fetNpService) {
        this.fetNpService = fetNpService;
    }
}
