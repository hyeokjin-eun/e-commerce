package com.ecommerce.template.common.utils;

public interface LoggerUtil {

    void error(Exception exception);

    void info(String message);

    void warn(String message);

    void debug(String message);
}
