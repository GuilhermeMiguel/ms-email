package com.ms.email.infraestructure.mapper;

import com.ms.email.domain.model.Email;
import com.ms.email.infraestructure.adapter.persistence.entity.EmailEntity;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public EmailEntity toEntity(Email email) {
        EmailEntity entity = new EmailEntity();
        entity.setEmailId(email.getEmailId());
        entity.setOwnerRef(email.getOwnerRef());
        entity.setEmailFrom(email.getEmailFrom());
        entity.setEmailTo(email.getEmailTo());
        entity.setSubject(email.getSubject());
        entity.setText(email.getText());
        entity.setSendDateEmail(email.getSendDateEmail());
        entity.setStatusEmail(email.getStatusEmail().name());
        return entity;
    }

    public Email toDomain(EmailEntity entity) {
        return new Email(
            entity.getEmailId(),
            entity.getOwnerRef(),
            entity.getEmailFrom(),
            entity.getEmailTo(),
            entity.getSubject(),
            entity.getText(),
            entity.getSendDateEmail(),
            com.ms.email.domain.model.enums.StatusEmail.valueOf(entity.getStatusEmail())
        );
    }
}
