package com.ms.email.infraestructure.adapter.request;

import com.ms.email.domain.model.Email;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmailRequest {

    @NotBlank
    private String ownerRef;
    @NotBlank
    @javax.validation.constraints.Email
    private String emailFrom;
    @NotBlank
    @javax.validation.constraints.Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;

    public Email toEmail() {
        Email email = new Email();
        email.setOwnerRef(this.ownerRef);
        email.setEmailFrom(this.emailFrom);
        email.setEmailTo(this.emailTo);
        email.setSubject(this.subject);
        email.setText(this.text);
        return email;
    }
}
