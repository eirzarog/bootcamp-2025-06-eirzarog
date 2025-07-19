package dev.redbeans.bootcamp202506eirzarog.springapi.repositories;

import dev.redbeans.bootcamp202506eirzarog.springapi.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository <Message, Long> {
}
