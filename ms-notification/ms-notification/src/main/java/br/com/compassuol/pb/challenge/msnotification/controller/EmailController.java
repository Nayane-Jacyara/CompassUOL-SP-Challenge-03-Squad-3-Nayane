package br.com.compassuol.pb.challenge.msnotification.controller;

import br.com.compassuol.pb.challenge.msnotification.dtos.EmailDTO;
import br.com.compassuol.pb.challenge.msnotification.entity.Email;
import br.com.compassuol.pb.challenge.msnotification.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDto) {
        Email emailModel = new Email();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}


