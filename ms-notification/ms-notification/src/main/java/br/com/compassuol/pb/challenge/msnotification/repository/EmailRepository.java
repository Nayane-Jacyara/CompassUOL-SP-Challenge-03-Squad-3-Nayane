package br.com.compassuol.pb.challenge.msnotification.repository;

import br.com.compassuol.pb.challenge.msnotification.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
