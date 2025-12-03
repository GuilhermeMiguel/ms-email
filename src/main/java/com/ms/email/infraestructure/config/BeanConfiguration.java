package com.ms.email.infraestructure.config;

import com.ms.email.application.usecase.EmailUseCaseImpl;
import com.ms.email.domain.port.in.EmailUseCase;
import com.ms.email.domain.port.out.EmailRepositoryPort;
import com.ms.email.domain.port.out.EmailSenderPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public EmailUseCase emailUseCase(EmailRepositoryPort emailRepositoryPort, 
                                   EmailSenderPort emailSenderPort) {
        return new EmailUseCaseImpl(emailRepositoryPort, emailSenderPort);
    }
}
