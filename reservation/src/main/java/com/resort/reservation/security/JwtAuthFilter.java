package com.resort.reservation.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();

        // ✅ Skip filter for UI + static resources
        if (path.equals("/") ||
            path.equals("/index.html") ||
            path.endsWith(".html") ||
            path.startsWith("/css/") ||
            path.startsWith("/js/") ||
            path.startsWith("/images/") ||
            path.equals("/favicon.ico") ||
            path.startsWith("/api/auth")) {
            chain.doFilter(req, res);
            return;
        }

        // ✅ For protected APIs, require Bearer token
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(401);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        String token = header.substring(7);

        try {
            Claims claims = JwtUtil.validateToken(token);
            request.setAttribute("username", claims.getSubject());
            request.setAttribute("role", claims.get("role"));
            chain.doFilter(req, res);
        } catch (Exception e) {
            response.setStatus(401);
            response.getWriter().write("Invalid or expired token");
        }
    }
}