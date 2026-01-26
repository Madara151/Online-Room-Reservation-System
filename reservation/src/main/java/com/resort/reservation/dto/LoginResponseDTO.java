package com.resort.reservation.dto;

public class LoginResponseDTO {


private String message;
private String username;
private String role;
private String token; // ✅ add this


public LoginResponseDTO() {}


public LoginResponseDTO(String message, String username, String role) {
this.message = message;
this.username = username;
this.role = role;
}


public String getMessage() { return message; }
public String getUsername() { return username; }
public String getRole() { return role; }
public String getToken() { return token; }


public void setMessage(String message) { this.message = message; }
public void setUsername(String username) { this.username = username; }
public void setRole(String role) { this.role = role; }
public void setToken(String token) { this.token = token; }
}