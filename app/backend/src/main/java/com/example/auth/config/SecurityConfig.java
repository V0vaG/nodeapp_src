package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // אופציונלי: לבטל CSRF בשביל POST
            .authorizeHttpRequests()
                .anyRequest().permitAll()
            .and()
            .formLogin().disable(); // לא להשתמש בטופס login של Spring

        return http.build();
    }
}
