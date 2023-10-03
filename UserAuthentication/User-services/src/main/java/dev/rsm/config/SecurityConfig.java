package dev.rsm.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<UserHeaderFilter> customHeaderFilter() {
        FilterRegistrationBean<UserHeaderFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserHeaderFilter());
        return registrationBean;
    }

}
