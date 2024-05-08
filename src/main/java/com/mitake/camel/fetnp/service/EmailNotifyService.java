package com.mitake.camel.fetnp.service;

import org.apache.camel.Exchange;
import org.apache.camel.support.MessageHelper;
import org.apache.camel.support.processor.DefaultExchangeFormatter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class EmailNotifyService {
    private static Logger logger = LoggerFactory.getLogger(EmailNotifyService.class);

    private String subjectTitle = null;
    private String from = null;
    private String to = null;
    private String cc = null;

    public void messageHandler(Exchange exchange) throws Exception {

        StringBuffer body = null;
        String subject = null;
        String messageHistory = null;
        String stackTrace = null;
        String breadcrumb_id = null;
        Exception exception = null;
        String fileName = null;

        fileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
        exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        breadcrumb_id = exchange.getIn().getHeader(Exchange.BREADCRUMB_ID, String.class);

        body = new StringBuffer();

        DefaultExchangeFormatter formatter = new DefaultExchangeFormatter();
        formatter.setShowExchangeId(true);
        formatter.setMultiline(true);
        formatter.setShowHeaders(true);
        formatter.setStyle(DefaultExchangeFormatter.OutputStyle.Fixed);

        messageHistory = MessageHelper.dumpMessageHistoryStacktrace(exchange, formatter, true);
        stackTrace = ExceptionUtils.getStackTrace(exception);

        subject = subjectTitle + ", ex=" + ExceptionUtils.getRootCauseMessage(exception);
        body.append(MessageFormat.format("BREADCRUMB_ID={0}\r\nFileName={1}\r\nException={2}\r\n{3}\r\n{4}",
                breadcrumb_id, fileName, exception.getMessage(), messageHistory, stackTrace));

        logger.error(subject);
        logger.error(body.toString());

        exchange.getIn().setHeader("subject", subject);
        exchange.getIn().setHeader("from", from);
        exchange.getIn().setHeader("to", to);
        exchange.getIn().setHeader("cc", cc);
        exchange.getIn().setHeader("contentType", "text/plain");
        exchange.getIn().setBody(body.toString());
    }


    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

}
