package dev.redbeans.bootcamp202506eirzarog.springapi.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private Long chat_id;

    @Column(name = "text", length = Integer.MAX_VALUE)
    private String text;

    @Column(name = "sender", length = 50)
    private String sender;         // π.χ. "user" ή "ai"

    @Column(name = "is_user_message")
    private boolean isUserMessage; // true αν στάλθηκε από χρήστη

    @Column(name = "created_at")
    private Instant createdAt;
}
