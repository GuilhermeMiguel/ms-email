package com.ms.email.domain.port.out;

import com.ms.email.domain.model.Email;

public interface EmailSenderPort {
    void sendEmail(Email email);
}
