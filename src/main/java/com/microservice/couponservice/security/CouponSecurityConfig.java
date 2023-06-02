package com.microservice.couponservice.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class CouponSecurityConfig {
    @Bean
    public SecurityFilterChain couponSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers(HttpMethod.GET,"/coupons/retrieve/**").permitAll()
                        .requestMatchers("/actuator/**", "/actuator/logger/^[\\w\\.]+").permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated());
                        //.requestMatchers(HttpMethod.POST,"/coupons/create").authenticated());
        http.csrf(csrfCustomizer -> csrfCustomizer.ignoringRequestMatchers(
                new RegexRequestMatcher("/coupons/create", "POST")));
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        http.headers(header->header.frameOptions().sameOrigin()).sessionManagement(
                session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }
}
