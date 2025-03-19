package com.xiaoming.spring.config;

import com.xiaoming.spring.security.HL7EncryptionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/registrations/**").permitAll()
                        .requestMatchers("/api/hl7/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new HL7EncryptionFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }
}
