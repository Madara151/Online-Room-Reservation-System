package com.resort.reservation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        // ✅ JWT is stateless
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // ✅ Allow UI + auth endpoints
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/", "/index.html",
                        "/dashboard.html", "/reservations.html", "/billing.html", "/reports.html", "/help.html",
                        "/css/**", "/js/**", "/images/**", "/favicon.ico",
                        "/error"
                ).permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
        );

        // ✅ IMPORTANT: add JWT filter into Spring Security chain
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}