package dev.redbeans.bootcamp202506eirzarog.springapi.controllers;

import dev.redbeans.bootcamp202506eirzarog.springapi.exceptions.BootcampException;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.Chat;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.User;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.criteria.ChatCriteria;
import dev.redbeans.bootcamp202506eirzarog.springapi.repositories.ChatRepository;
import dev.redbeans.bootcamp202506eirzarog.springapi.repositories.UserRepository;
import dev.redbeans.bootcamp202506eirzarog.springapi.services.ChatService;
import dev.redbeans.bootcamp202506eirzarog.springapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {

    Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final UserRepository userRepository;
    private final  ChatRepository chatRepository;
    private UserService userService;
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatRepository chatRepository,
                          ChatService chatService,
                          UserRepository userRepository,
                          UserService userService) {
        this.chatRepository = chatRepository;
        this.chatService = chatService;
        this.userRepository = userRepository;
        this.userService = userService;

        logger.debug("ChatController initialized with ChatService.  Reference to:{}", this);

    }


//    @GetMapping("/chats")
//    public List<Chat> getAllChats(ChatCriteria criteria) throws BootcampException {
//        // validate that the login username is equal to the username parameter
//        return chatService.getAll(criteria);
//    }

    @GetMapping("/chats/{username}")
    public List<Chat> getAllChatsByUsername(@PathVariable String username)  {

        // validate that the login username is equal to the username parameter
        // add authentication/authorization logic here

       return chatRepository.findChatsByUsername(username);


    }


    // Get all chats with optional criteria
    // the parameters are case-sensitive
    // if a parameter is null, it will not filter by that parameter and return all chats
    // Example: http://localhost:8080/chats?userId=1&to=2025-06-19T00:00:00.905Z
    // the below method would have Criteria object as parameter
    @GetMapping("/chats")
    public ResponseEntity<List<Chat>> getAllChats(@RequestParam (required = false) Long userId,
                                                  @RequestParam(required = false) String username,
                                                  @RequestParam(required = false) Instant from,
                                                  @RequestParam(required = false) Instant to) throws BootcampException {

        // Create ChatCriteria object from request parameters
        ChatCriteria criteria = new ChatCriteria();
        criteria.setUserId(userId);
        criteria.setUsername(username);
        criteria.setFrom(from);
        criteria.setTo(to);

        // Fetch all chats based on criteria
        //   return chats;
        //return new ResponseEntity<>(chatRepository.findAll(userId, username, from, to), HttpStatus.OK);
        return new ResponseEntity<>(chatService.getAll(criteria), HttpStatus.OK);
    }

/*
    Example of exited from circular dependency using @Lazy
    private UserController userController;

    @Autowired
    public ChatController(@Lazy UserController userController) {
        this.userController = userController;
    }
*/
}
