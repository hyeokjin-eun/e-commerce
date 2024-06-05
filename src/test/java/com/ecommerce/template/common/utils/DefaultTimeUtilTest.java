package com.ecommerce.template.common.utils;

import com.ecommerce.template.common.utils.impl.DefaultTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("공통_타임_유틸_테스트")
public class DefaultTimeUtilTest {

    private TimeUtil timeUtil;

    @BeforeEach
    public void setUp() {
        this.timeUtil = new DefaultTimeUtil();
    }

    @Test
    public void LocalDate_객체를_생성한다() {
        int year = 2024;
        int month = 1;
        int day = 1;
        LocalDate localDate = timeUtil.ofLocalDate(year, month, day);
        assertThat(localDate.getYear()).isEqualTo(year);
        assertThat(localDate.getMonthValue()).isEqualTo(month);
        assertThat(localDate.getDayOfMonth()).isEqualTo(day);
    }

    @Test
    public void LocalDateTime_객체를_반환한다() {
        LocalDateTime localDateTime = timeUtil.getCurrentTime();
        assertThat(localDateTime).isNotNull();
    }
}
