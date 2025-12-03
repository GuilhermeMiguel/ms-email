package com.ms.email.infraestructure.adapter.controllers;

import com.ms.email.domain.model.Email;
import com.ms.email.domain.port.in.EmailUseCase;
import com.ms.email.infraestructure.adapter.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmailController {

    private final EmailUseCase emailUseCase;

    @PostMapping("/emails")
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailRequest emailRequest) {
        Email sentEmail = emailUseCase.sendEmail(emailRequest.toEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(sentEmail);
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<Email>> getAllEmails(
            @PageableDefault(size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(emailUseCase.findAll(pageable));
    }

    @GetMapping("/emails/{emailId}")
    public ResponseEntity<Email> getEmailById(@PathVariable UUID emailId) {
        return ResponseEntity.ok(emailUseCase.findById(emailId));
    }
}
