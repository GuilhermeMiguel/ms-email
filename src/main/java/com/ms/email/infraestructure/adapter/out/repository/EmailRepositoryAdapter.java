package com.ms.email.infraestructure.adapter.out.repository;

import com.ms.email.domain.model.Email;
import com.ms.email.domain.port.out.EmailRepositoryPort;
import com.ms.email.infraestructure.adapter.persistence.repositories.EmailRepository;
import com.ms.email.infraestructure.mapper.EmailMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class EmailRepositoryAdapter implements EmailRepositoryPort {

    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;

    public EmailRepositoryAdapter(EmailRepository emailRepository, EmailMapper emailMapper) {
        this.emailRepository = emailRepository;
        this.emailMapper = emailMapper;
    }

    @Override
    public Email save(Email email) {
        var entity = emailMapper.toEntity(email);
        var savedEntity = emailRepository.save(entity);
        return emailMapper.toDomain(savedEntity);
    }

    @Override
    public Page<Email> findAll(Pageable pageable) {
        return emailRepository.findAll(pageable).map(emailMapper::toDomain);
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        return emailRepository.findById(emailId).map(emailMapper::toDomain);
    }
}
