package com.ms.email.domain.port.in;

import com.ms.email.domain.model.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EmailUseCase {
    Email sendEmail(Email email);
    Page<Email> findAll(Pageable pageable);
    Email findById(UUID emailId);
}
