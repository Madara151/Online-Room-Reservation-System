package com.resort.reservation.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;


import java.security.Key;
import java.util.Date;


public class JwtUtil {


// NOTE: When the app restarts, the key changes.
// For an assignment, this is OK. For real apps, store key in config.
private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


private static final long EXPIRY_MS = 1000 * 60 * 60; // 1 hour


public static String generateToken(String username, String role) {
return Jwts.builder()
.setSubject(username)
.claim("role", role)
.setIssuedAt(new Date())
.setExpiration(new Date(System.currentTimeMillis() + EXPIRY_MS))
.signWith(KEY)
.compact();
}


public static Claims validateToken(String token) {
return Jwts.parserBuilder()
.setSigningKey(KEY)
.build()
.parseClaimsJws(token)
.getBody();
}
}