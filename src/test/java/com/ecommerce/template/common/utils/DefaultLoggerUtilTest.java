package com.ecommerce.template.common.utils;

import com.ecommerce.template.common.utils.impl.DefaultLoggerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
@DisplayName("공통_로그_유틸_테스트")
@Order(3)
public class DefaultLoggerUtilTest {

    private LoggerUtil loggerUtil;

    @BeforeEach
    public void setUp() {
        loggerUtil = new DefaultLoggerUtil();
    }

    @Test
    public void 에러_로그를_출력한다(CapturedOutput capturedOutput) {
        RuntimeException runtimeException = new RuntimeException("Test Exception");
        loggerUtil.error(runtimeException);
        assertThat(capturedOutput.toString()).contains("Test Exception");
    }

    @Test
    public void 인포_로그를_출력한다(CapturedOutput capturedOutput) {
        loggerUtil.info("Info Output Test");
        assertThat(capturedOutput.toString()).contains("Info Output Test");
    }

    @Test
    public void 워닝_로그를_출력한다(CapturedOutput capturedOutput) {
        loggerUtil.warn("Warn Output Test");
        assertThat(capturedOutput.toString()).contains("Warn Output Test");
    }
}
