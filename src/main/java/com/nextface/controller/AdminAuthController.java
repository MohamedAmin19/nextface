package com.nextface.controller;

import com.nextface.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {
    @Value("${admin.username}") private String adminUser;
    @Value("${admin.password}") private String adminPass;
    private final JwtUtil jwtUtil;

    public AdminAuthController(JwtUtil jwtUtil) { this.jwtUtil = jwtUtil; }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> creds) {
        if (creds.get("username").equals(adminUser) && creds.get("password").equals(adminPass)) {
            return Map.of("token", jwtUtil.generateToken(adminUser));
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }
}