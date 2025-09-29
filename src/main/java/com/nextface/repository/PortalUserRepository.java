package com.nextface.repository;

import com.nextface.entity.PortalUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface PortalUserRepository extends JpaRepository<PortalUser, Long> {
    Page<PortalUser> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
