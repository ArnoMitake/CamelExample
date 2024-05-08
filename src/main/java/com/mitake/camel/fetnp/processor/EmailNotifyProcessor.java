package com.mitake.camel.fetnp.processor;

import com.mitake.camel.fetnp.service.EmailNotifyService;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotifyProcessor implements Processor {

    private static Logger logger = LoggerFactory
            .getLogger(EmailNotifyProcessor.class);

    private EmailNotifyService emailNotifyService = null;

    public void process(Exchange exchange) throws Exception {

        emailNotifyService.messageHandler(exchange);

    }

    public EmailNotifyService getEmailNotifyService() {
        return emailNotifyService;
    }

    public void setEmailNotifyService(EmailNotifyService emailNotifyService) {
        this.emailNotifyService = emailNotifyService;
    }
}
