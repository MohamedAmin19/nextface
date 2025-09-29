package com.nextface.controller;

import com.nextface.entity.PortalUser;
import com.nextface.repository.PortalUserRepository;
import com.nextface.security.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {
    private final PortalUserRepository repo;
    private final JwtUtil jwtUtil;

    public AdminUserController(PortalUserRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public Page<PortalUser> getUsers(@RequestParam(defaultValue = "") String name,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header missing or invalid");
        }
        String token = authHeader.replace("Bearer ", "");
        try {
            String user = jwtUtil.validateToken(token);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
            }
        } catch (io.jsonwebtoken.JwtException e) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repo.findAllByNameContainingIgnoreCase(name, pageable);
    }
}
