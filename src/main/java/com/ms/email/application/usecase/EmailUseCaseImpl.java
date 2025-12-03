package com.ms.email.application.usecase;

import com.ms.email.domain.exception.EmailNotFoundException;
import com.ms.email.domain.model.Email;
import com.ms.email.domain.port.out.EmailRepositoryPort;
import com.ms.email.domain.port.out.EmailSenderPort;
import com.ms.email.domain.port.in.EmailUseCase;
import com.ms.email.domain.model.enums.StatusEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmailUseCaseImpl implements EmailUseCase {

    private final EmailRepositoryPort emailRepository;
    private final EmailSenderPort emailSender;

    public EmailUseCaseImpl(EmailRepositoryPort emailRepository, EmailSenderPort emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Override
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        email.setStatusEmail(StatusEmail.PROCESSING);
        
        // Salva primeiro com status PROCESSING
        Email savedEmail = emailRepository.save(email);
        
        try {
            // Tenta enviar o email
            emailSender.sendEmail(savedEmail);
            savedEmail.setStatusEmail(StatusEmail.SENT);
        } catch (Exception e) {
            savedEmail.setStatusEmail(StatusEmail.ERROR);
        }
        
        // Atualiza o status final
        return emailRepository.save(savedEmail);
    }

    @Override
    public Page<Email> findAll(Pageable pageable) {
        return emailRepository.findAll(pageable);
    }

    @Override
    public Email findById(UUID emailId) {
        return emailRepository.findById(emailId)
                .orElseThrow(() -> new EmailNotFoundException("Email not found for id " + emailId));
    }
}
