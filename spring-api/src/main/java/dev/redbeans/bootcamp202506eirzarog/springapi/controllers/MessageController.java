package dev.redbeans.bootcamp202506eirzarog.springapi.controllers;

import dev.redbeans.bootcamp202506eirzarog.springapi.models.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class MessageController {


        private final List<Message> messages = new ArrayList<>();
//    Αντί για List<Message>, μπορείς να δουλέψεις με βάση δεδομένων και Repository.
//    Αν έχεις authentication, μπορείς να συνδέσεις τα μηνύματα με χρήστη.
//    Μπορείς να αντικαταστήσεις το stub της AI με κανονικό integration σε
//    AI μοντέλο ή API (π.χ. Azure OpenAI, HuggingFace, κ.ά.).
//
        @PostMapping("/messages/send")
        public ResponseEntity<String> sendMessage(@RequestBody Message message) {
            String sender = message.getSender();
            String text = message.getText();

            // Here you can add your message processing logic (e.g., save to DB, notify recipients)
            System.out.printf("Received message from %s: %s%n", sender, text);

            return ResponseEntity.ok("Message sent successfully");
        }

        public Message aiSendMessage(@RequestBody Message message) {
            message.setCreatedAt(Instant.now());
            messages.add(message);

            // Παράδειγμα απάντησης AI (απλό stub)
            Message aiResponse = new Message();
            aiResponse.setSender("ai");
            aiResponse.setText("Έλα να τα πούμε! 😊 Πες μου περισσότερα.");
            aiResponse.setCreatedAt(Instant.now());
            aiResponse.setUserMessage(false);
            messages.add(aiResponse);

            return aiResponse;
        }

        @GetMapping("/messages")
        public List<Message> getAllMessages() {
            return messages;
        }

}
