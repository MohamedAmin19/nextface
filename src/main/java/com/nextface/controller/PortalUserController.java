package com.nextface.controller;

import com.nextface.entity.PortalUser;
import com.nextface.repository.PortalUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/portal/users")
public class PortalUserController {
    private final PortalUserRepository repo;

    public PortalUserController(PortalUserRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/register")
    public String register(@RequestBody PortalUser user) {
        if (repo.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already registered");
        }
        repo.save(user);
        return "Registered successfully";
    }

    @ExceptionHandler(ResponseStatusException.class)
    public String handleResponseStatusException(ResponseStatusException e) {
        return e.getReason();
    }
}
