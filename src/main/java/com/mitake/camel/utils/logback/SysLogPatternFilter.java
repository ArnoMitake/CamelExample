package com.mitake.camel.utils.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class SysLogPatternFilter extends Filter<ILoggingEvent> {
    private static final String SYSLOG_PATTERN = "SL-";

    public SysLogPatternFilter() {
    }

    public FilterReply decide(ILoggingEvent event) {
        return (Level.DEBUG.equals(event.getLevel()) || Level.INFO.equals(event.getLevel())) && !event.getMessage().startsWith("SL-") ? FilterReply.DENY : FilterReply.ACCEPT;
    }
}
