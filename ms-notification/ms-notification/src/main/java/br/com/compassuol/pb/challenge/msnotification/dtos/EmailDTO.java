package br.com.compassuol.pb.challenge.msnotification.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.BeanUtils;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EmailDTO {


    @NotBlank
    private String ownerRef;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;

    /*@NotBlank
    @Email
    private String fromEmail;
    @NotBlank
    private String fromName;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String replyTo;
    @NotBlank
    private  String subject;
    @NotBlank
    private String body;
    @NotBlank
    private String contentType;*/


}
