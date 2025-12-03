package com.ms.email.infraestructure.adapter.consumers;

import com.ms.email.domain.model.Email;
import com.ms.email.domain.port.in.EmailUseCase;
import com.ms.email.infraestructure.adapter.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailUseCase emailUseCase;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailRequest emailRequest) {
        Email email = new Email();
        BeanUtils.copyProperties(emailRequest, email);
        emailUseCase.sendEmail(email);
        System.out.println("Email Status: " + email.getStatusEmail().toString());
    }
}
