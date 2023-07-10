package br.com.compassuol.pb.challenge.msnotification.entity;


import br.com.compassuol.pb.challenge.msnotification.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_email")
public class Email implements Serializable {

    private static final long serialVersionUID =1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID emailId;
    private  String ownerRef;//referencia do propietario que estar mandado sm msg,
    // vai rwe repassando o id d=paar quem vai ser usando esse email
    private  String emailFrom;// quem estar mandado
    private  String emailTo;// para quem vai ser enviado
    private  String subject;
    @Column(columnDefinition = "TEXT")
    private String text;// msg do email
    private LocalDateTime sendDataEmail;
    private StatusEmail statusEmail; // se foi enviado ou nao

}
