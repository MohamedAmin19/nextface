package com.nextface.repository;

import com.nextface.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    boolean existsByEmail(String email);
}
