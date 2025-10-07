package com.nextface.controller;

import com.nextface.entity.Subscribe;
import com.nextface.service.SubscribeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class SubscribeController {
    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @PostMapping
    public ResponseEntity<String> addEmail(@RequestBody EmailRequest request) {
        String message = subscribeService.saveEmail(request.getEmail());

        if (message.equals("Email already subscribed")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public ResponseEntity<Page<Subscribe>> getAllEmails(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<Subscribe> emails = subscribeService.getAllEmails(email, page, size);
        return ResponseEntity.ok(emails);
    }

    public static class EmailRequest {
        private String email;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
