package com.example.springweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        security.httpBasic(Customizer.withDefaults());
        //security.csrf().disable();
        return security.build();
    }
}
