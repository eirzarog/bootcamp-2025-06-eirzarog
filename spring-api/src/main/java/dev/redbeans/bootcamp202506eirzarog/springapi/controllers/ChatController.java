package dev.redbeans.bootcamp202506eirzarog.springapi.controllers;

import dev.redbeans.bootcamp202506eirzarog.springapi.exceptions.BootcampException;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.Chat;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.User;
import dev.redbeans.bootcamp202506eirzarog.springapi.repositories.ChatRepository;
import dev.redbeans.bootcamp202506eirzarog.springapi.repositories.UserRepository;
import dev.redbeans.bootcamp202506eirzarog.springapi.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {

    private final  ChatRepository chatRepository;
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatRepository chatRepository, ChatService chatService) {
        this.chatRepository = chatRepository;
        this.chatService = chatService;

    }

    @GetMapping("/chats/{username}")
    public List<Chat> getChatsByUsername(@PathVariable String username)  {

        // validate that the login username is equal to the username parameter
        // TODO: Add authentication/authorization logic here

        return chatRepository.findChatsByUsername(username);
      //  return chatService.getChatsByUsername(username);
    }


    @GetMapping("/chats")
    public List<Chat> getAllChats(){

//        return chats;
        return chatRepository.findAll();
    }

    // Example of circular dependency resolution using @Lazy
//    private UserController userController;
//
//    @Autowired
//    public ChatController(@Lazy UserController userController) {
//        this.userController = userController;
//    }
}
