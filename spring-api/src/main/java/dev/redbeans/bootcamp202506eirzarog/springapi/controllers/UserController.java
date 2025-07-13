package dev.redbeans.bootcamp202506eirzarog.springapi.controllers;

import dev.redbeans.bootcamp202506eirzarog.springapi.exceptions.BootcampException;
import dev.redbeans.bootcamp202506eirzarog.springapi.exceptions.BootcampExceptionHandler;
import dev.redbeans.bootcamp202506eirzarog.springapi.exceptions.ErrorMessage;
import dev.redbeans.bootcamp202506eirzarog.springapi.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

// This is a placeholder for the UserController class in a Spring Boot application.
// It is annotated with @RestController, which indicates that it is a controller
// that handles HTTP requests and returns responses in a RESTful manner.

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
public class UserController {

//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


//  I suppose that this is my database a list of users.
    private List<User> users = new ArrayList<>();

    public UserController(){
        // Initializing with some dummy data
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("john_doe");
        user1.setEmail("jdoe@example.com");
        user1.setPassword("password123");
        user1.setName("John Doe");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("nick_morris");
        user2.setEmail("nmorris@redbeans.com");
        user2.setPassword("password456");
        user2.setName("Nick Morris");

        // Adding users to the list
        users.add(user1);
        users.add(user2);

//        users.add(new User("john_doe", "jdoe@example.com", "password123", "John Doe"));
//        users.add(new User("nick_morris", "nmorris@redbeans.com", "password456", "Nick Morris"));


    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return users;
    }

    // id is a path variable that represents the unique identifier of a user.

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        // Logic to find and return the user by id
//        Yes, Java Streams are conceptually similar to C# LINQ. Both provide a way to process collections
//        using functional-style operations like
//        filter, map, and reduce (Java) or Where, Select, and Aggregate (C#).
//        They enable concise, declarative data manipulation. Επιτρέπουν συνοπτικό, δηλωτικό χειρισμό δεδομένων.
//        return users.stream()
//                .filter(user -> user.getUsername().equals(id))
//                .findFirst()
//                .orElse(null);

        for (User user : users) {
            if (user.getUsername().equals(username)) {
               return user;
            }

        }
        return null; // or throw an exception if user not found
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception {
        // Validate the user object, e.g., check for null fields, unique username, etc.
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                //logger.error("Username with username: " + user.getUsername() + " already exists.");
//                throw new Exception("Username with username: " + user.getUsername() + " already exists."); // breakpoint here
                throw new BootcampException(HttpStatus.BAD_REQUEST,"Username with username: " + user.getUsername() + " already exists."); // breakpoint here

            }
        }
        user.setId(users.size() + 1L); // Assign a new ID based on the current size of the list

        // Logic to create a new user
        users.add(user);
        return user; // Return the created user
    }

//    @PostMapping("/users")
//    public ResponseEntity<Object> createUser(@RequestBody User user) {
//        // Validate the user object, e.g., check for null fields, unique username, etc.
//        for (User existingUser : users) {
//            if (existingUser.getUsername().equals(user.getUsername())) {
//                HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//
//                ErrorMessage errorMessage = new ErrorMessage();
//                errorMessage.setErrorMessage("Username with username: " + user.getUsername() + " already exists.");
//                errorMessage.setErrorCode(status.value());
//
//                return ResponseEntity.status(status).body(errorMessage);
//            }
//        }
//        user.setId(users.size() + 1L); // Assign a new ID based on the current size of the list
//
//        // Logic to create a new user
//        users.add(user);
////        return user; // Return the created user
//        return ResponseEntity.ok(user); // Return the created user with HTTP 500 OK status
//    }


    //    Spring Boot uses Jackson ObjectMapper (by default) to automatically map JSON data from HTTP requests to Java objects.
//    When you annotate a controller method parameter with @RequestBody, Spring Boot deserializes the incoming JSON
//    into the specified Java class using Jackson, matching JSON fields to Java fields by name. The class must have//
//    getters and setters for its properties.
    @PutMapping("/users/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User updatedUser) throws Exception {
        // Logic to find the user by username and update their information
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username)) {
                // Update the user's information
                user.setEmail(updatedUser.getEmail());
                user.setPassword(updatedUser.getPassword());
                user.setName(updatedUser.getName());
                return user; // Return the updated user
            }
        }
        throw new Exception("User with username: " + username + " not found.");
    }

    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable String username) throws Exception {
        // Logic to find the user by username and delete them
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username)) {
                users.remove(i); // Remove the user from the list
                return "User with username: " + username + " has been deleted.";
            }
        }
        throw new Exception("User with username: " + username + " not found.");
    }

    // This class is currently empty, but you can add methods to handle user-related requests.
    // For example, you might want to add methods to create, update, delete, or retrieve user information.
    // You can use annotations like @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping
    // to define the endpoints for these operations.

    // Example method:
    // @GetMapping("/users")
    // public List<User> getAllUsers() {
    //     return userService.getAllUsers();
    // }

    // You can also inject services or repositories to handle business logic and data access.
    // For example:
    // @Autowired

    // private UserService userService;
    // public UserController(UserService userService) {
    //     this.userService = userService;
    // }
    // You can also define request parameters, path variables, and request bodies
    // using annotations like @RequestParam, @PathVariable, and @RequestBody.
    // For example:
    // @PostMapping("/users")
    // public User createUser(@RequestBody User user) {
    //     return userService.createUser(user);
    // }
    // Remember to handle exceptions and return appropriate HTTP status codes
    // using annotations like @ResponseStatus or by throwing exceptions that are handled by a global exception handler.
    // You can also use @RestControllerAdvice to handle exceptions globally for your controllers.
    // This class can be expanded with methods to handle user-related operations.
    // For example, you might want to add methods to create, update, delete, or retrieve user information.
    // Example method:
    // @GetMapping("/users/{id}")
    // public User getUserById(@PathVariable Long id) {
    //     return userService.getUserById(id);
    // }
    // You can also use annotations like @RequestMapping to define the base path for all methods in this controller.
    // @RequestMapping("/users")
    // public class UserController {
    //     // Methods for user operations go here
    // }
    // You can also use @Autowired to inject services or repositories to handle business logic and data access.
    // For example:
    // @Autowired
    // private UserService userService;
    // public UserController(UserService userService) {
    //     this.userService = userService;
}