package com.ms.email.infraestructure.adapter.persistence.repositories;

import com.ms.email.infraestructure.adapter.persistence.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailEntity, UUID> {
}
