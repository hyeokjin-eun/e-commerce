package com.ecommerce.template.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class WebConfig {

    private final String[] ignoreList = new String[]{
            "/static/js/**",
            "/static/images/**",
            "/static/css/**",
            "/static/scss/**",
            "/h2-console/**"
    };

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (webSecurityConfiguration) -> webSecurityConfiguration.ignoring()
                .requestMatchers(ignoreList);
    }
}
