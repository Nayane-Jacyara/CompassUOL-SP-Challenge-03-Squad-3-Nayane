package br.com.compassuol.pb.challenge.msnotification.consumers;

import br.com.compassuol.pb.challenge.msnotification.dtos.EmailDTO;
import br.com.compassuol.pb.challenge.msnotification.entity.Email;
import br.com.compassuol.pb.challenge.msnotification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailComsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO){
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO,email);
        emailService.sendEmail(email);
        System.out.println("Email Status:" + email.getStatusEmail().toString());
    }
}


