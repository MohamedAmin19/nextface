package com.nextface.repository;

import com.nextface.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    boolean existsByEmail(String email);
    List<Subscribe> findByEmail(String email);
}
