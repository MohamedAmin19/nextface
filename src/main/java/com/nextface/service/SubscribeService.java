package com.nextface.service;

import com.nextface.entity.Subscribe;
import com.nextface.repository.SubscribeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;

    public SubscribeService(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    public String saveEmail(String address) {
        if (subscribeRepository.existsByEmail(address)) {
            return "Email already subscribed";
        }

        Subscribe email = new Subscribe();
        email.setEmail(address);
        subscribeRepository.save(email);
        return "Subscribed successfully";
    }

    public Page<Subscribe> getAllEmails(String email, int page, int size) {
        if (email != null && !email.isEmpty()) {
            return subscribeRepository.findByEmailContaining(email, PageRequest.of(page, size));
        }
        return subscribeRepository.findAll(PageRequest.of(page, size));
    }
}
