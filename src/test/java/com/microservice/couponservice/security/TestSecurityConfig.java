package com.microservice.couponservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile({"test"})
public class TestSecurityConfig {
    @Bean
    public SecurityFilterChain couponTestSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        httpSecurity.formLogin().disable();
        httpSecurity.httpBasic(Customizer.withDefaults()).csrf().disable();
        return httpSecurity.build();
    }

}
