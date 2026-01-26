package com.resort.reservation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


http.csrf(csrf -> csrf.disable());


http.authorizeHttpRequests(auth -> auth
// ✅ Allow Web UI pages + static files
.requestMatchers(
"/", "/index.html",
"/dashboard.html", "/reservations.html", "/billing.html", "/reports.html", "/help.html",
"/css/**", "/js/**", "/images/**", "/favicon.ico",
"/error"
).permitAll()


// ✅ Allow auth endpoints
.requestMatchers("/api/auth/**").permitAll()


// ✅ Protect everything else (all APIs)
.anyRequest().authenticated()
);


return http.build();
}
}