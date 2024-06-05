package com.ecommerce.template.common.utils.impl;

import com.ecommerce.template.common.utils.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultLoggerUtil implements LoggerUtil {

    @Override
    public void error(Exception exception) {
        log.error(exception.getMessage());
    }

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void warn(String message) {
        log.warn(message);
    }
}
