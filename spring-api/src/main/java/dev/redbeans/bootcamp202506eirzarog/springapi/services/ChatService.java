package dev.redbeans.bootcamp202506eirzarog.springapi.services;

import dev.redbeans.bootcamp202506eirzarog.springapi.exceptions.BootcampException;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.Chat;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.User;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.criteria.ChatCriteria;
import dev.redbeans.bootcamp202506eirzarog.springapi.repositories.ChatRepository;
import dev.redbeans.bootcamp202506eirzarog.springapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public List<Chat> getChatsByUsername(String username) throws BootcampException {
        // Business validation - ensure user exists if username provided
        if (username != null) {
            User user = userRepository.findUserByUsername(username);
            if (!user.getUsername().equals(username)) {
                throw new BootcampException(HttpStatus.NOT_FOUND, "User with username '" + username + "' not found.");
            }

            // If user exists, fetch chats by username
            return chatRepository.findChatsByUsername(username);
        }

        return new ArrayList<>();
    }

    public List<Chat> getAll(ChatCriteria criteria) throws BootcampException {

        Instant twoYearsAgo = Instant.now().minusSeconds(60 * 60 * 24 * 365 * 2);

        if (criteria.getFrom() != null && criteria.getFrom().isBefore(twoYearsAgo)) {
            throw new BootcampException(HttpStatus.BAD_REQUEST,
                    "The 'from' date cannot be more than two years ago.");
        }

        return chatRepository.findAll(criteria.getUserId(),
                criteria.getUsername(),
                criteria.getFrom(),
                criteria.getTo());
    }
}
