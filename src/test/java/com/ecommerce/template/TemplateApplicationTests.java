package com.ecommerce.template;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Order(0)
@DisplayName("공통_Spring_Context_테스트")
class TemplateApplicationTests {

    @Test
    void contextLoads() {
    }

}
