package com.ecommerce.template.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TimeUtil {

    LocalDateTime getCurrentTime();

    LocalDate ofLocalDate(int year, int month, int day);
}
