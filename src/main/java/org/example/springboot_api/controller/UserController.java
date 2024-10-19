package org.example.springboot_api.controller;

import org.example.springboot_api.model.User;
import org.example.springboot_api.model.UserList;
import org.example.springboot_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//   Create new User (both JSON and XML)
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String addUser(@RequestBody User user) {
         userService.saveUser(user);
         return "Data inserted Successful!!";
    }

//    Get the User Details using Id (both json & xml)
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all the Users (return wrapped in 'userList' root element for XML)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserList> getAllUsers() {
        List<User> users = userService.getAllUsers();
        UserList userList = new UserList(users);
        return ResponseEntity.ok(userList);
    }

    // Update a User By ID (both JSON and XML)
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

//    Delete the User Details using Id
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable String id) {
       return userService.deleteUserById(id);
    }

//   Delete all the Users
    @DeleteMapping
    public String deleteAllUsers() {
       return  userService.deleteAllUsers();
    }
}