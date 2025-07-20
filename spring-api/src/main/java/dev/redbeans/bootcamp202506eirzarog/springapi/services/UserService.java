package dev.redbeans.bootcamp202506eirzarog.springapi.services;

import dev.redbeans.bootcamp202506eirzarog.springapi.exceptions.BootcampException;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.User;
import dev.redbeans.bootcamp202506eirzarog.springapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        logger.debug("UserService initialized with UserRepository. Reference to:{}", this);
    }

    public User createUser(User user) throws BootcampException {
        //validate that user with the same username does not exist
        User existingUser = userRepository.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new BootcampException(HttpStatus.BAD_REQUEST, "User with username " + user.getUsername() + " already exists.");
        }

        user = userRepository.save(user);
        return user;
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    // This class can be used to implement user-related business logic
    // For example, methods to create, update, delete users, etc.
    // Currently, it is empty as per the provided context.

    // You can add methods like:
    // - createUser(User user)
    // - updateUser(Long userId, User user)
    // - deleteUser(Long userId)
    // - getUserById(Long userId)
    // - getAllUsers()
}
