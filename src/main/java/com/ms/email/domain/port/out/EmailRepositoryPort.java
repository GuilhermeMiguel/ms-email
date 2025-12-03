package com.ms.email.domain.port.out;

import com.ms.email.domain.model.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EmailRepositoryPort {
    Email save(Email email);
    Page<Email> findAll(Pageable pageable);
    Optional<Email> findById(UUID emailId);
}
