package com.mitake.camel.fetnp.processor;

import com.mitake.camel.fetnp.service.FetNp412Service;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class FetNp412Processor {
    protected static final Logger logger = LoggerFactory.getLogger(FetNp412Processor.class);
    private FetNp412Service fetNp412Service;

    public void batchInsert412ReceiverListData(Exchange ech) throws Exception {
        List<String> listData = null;
        StopWatch sw = new StopWatch("batchInsert412ReceiverListData");
        int result = 0;
        listData = ech.getIn().getBody(ArrayList.class);

        if (listData != null) {
            sw.start();
            result = fetNp412Service.batchInsert412ReceiverListData(listData);
            sw.stop();
        }
        if (result > 0) {
            logger.info("匯入412WaitToUpdateNP，DstNo {} 筆資料，耗時:{} ms", result, sw.getTotalTimeMillis());
        }
    }

    public FetNp412Service getFetNp412Service() {
        return fetNp412Service;
    }

    public void setFetNp412Service(FetNp412Service fetNp412Service) {
        this.fetNp412Service = fetNp412Service;
    }
}
