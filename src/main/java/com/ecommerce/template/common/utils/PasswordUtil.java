package com.ecommerce.template.common.utils;

public interface PasswordUtil {

    String encode(String password);

    boolean matches(String password, String encodedPassword);
}
