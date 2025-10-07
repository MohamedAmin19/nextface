package com.nextface.service;

import com.nextface.entity.Subscribe;
import com.nextface.repository.SubscribeRepository;
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

    public List<Subscribe> getAllEmails() {
        return subscribeRepository.findAll();
    }
}
