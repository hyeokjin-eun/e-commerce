package com.ecommerce.template.common.utils.impl;

import com.ecommerce.template.common.utils.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultLoggerUtil implements LoggerUtil {

    @Override
    public void printError(Exception exception) {
        log.error(exception.getMessage());
    }
}
