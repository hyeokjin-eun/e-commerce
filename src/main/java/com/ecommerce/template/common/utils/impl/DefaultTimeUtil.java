package com.ecommerce.template.common.utils.impl;

import com.ecommerce.template.common.utils.TimeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DefaultTimeUtil implements TimeUtil {

    @Override
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
