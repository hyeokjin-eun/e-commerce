package com.ecommerce.template.common.utils.impl;

import com.ecommerce.template.common.utils.TimeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DefaultTimeUtil implements TimeUtil {

    @Override
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDate ofLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
