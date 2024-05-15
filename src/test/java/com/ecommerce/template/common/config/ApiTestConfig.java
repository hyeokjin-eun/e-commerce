package com.ecommerce.template.common.config;

import com.ecommerce.template.common.utils.impl.DefaultLoggerUtil;
import com.ecommerce.template.common.utils.impl.DefaultTimeUtil;
import org.springframework.context.annotation.Import;

@Import({DefaultLoggerUtil.class, DefaultTimeUtil.class})
public class ApiTestConfig {
}
