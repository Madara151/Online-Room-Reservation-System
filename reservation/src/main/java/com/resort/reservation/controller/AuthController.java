package com.resort.reservation.controller;

import com.resort.reservation.dto.ApiResponseDTO;
import com.resort.reservation.dto.LoginRequestDTO;
import com.resort.reservation.dto.LoginResponseDTO;
import com.resort.reservation.entity.User;
import com.resort.reservation.security.JwtUtil;
import com.resort.reservation.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {


private final UserService userService;


public AuthController(UserService userService) {
this.userService = userService;
}


@PostMapping("/register")
public ApiResponseDTO<String> register(@RequestBody @Valid LoginRequestDTO req) {
User user = userService.registerUser(req.getUsername(), req.getPassword());
return new ApiResponseDTO<>("User registered", user.getUsername());
}


@PostMapping("/login")
public ApiResponseDTO<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO req) {


User user = userService.login(req.getUsername(), req.getPassword());


String token = JwtUtil.generateToken(user.getUsername(), user.getRole());


LoginResponseDTO res = new LoginResponseDTO("Login success", user.getUsername(), user.getRole());
res.setToken(token);


return new ApiResponseDTO<>("OK", res);
}
}