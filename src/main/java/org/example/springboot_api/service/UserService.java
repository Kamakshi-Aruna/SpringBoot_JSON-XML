package org.example.springboot_api.service;

import org.example.springboot_api.model.User;
import org.example.springboot_api.reository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//   Create a User
    public void saveUser(User user) {
         userRepository.save(user);
    }

//    Get the User Details by Id (both json & xml)
    public Optional<User> getUserById(String id) {
         Optional<User> op=userRepository.findById(id);
         if(op.isPresent()){
             return op;
         }else{
             return Optional.empty();
         }
    }

//    Get all the Users (both json & xml)
    public List<User> getAllUsers() {
        List<User> list=userRepository.findAll();
        if(list.isEmpty()){
            return null;
        }else{
            return list;
        }
    }

//    Update the User by Id (both json & xml)
    public String updateUserById(String id, User user) {
        Optional<User> op=userRepository.findById(id);
        if(op.isPresent()) {
            User user1=op.get();
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setAge(user.getAge());
            userRepository.save(user1);
            return "Data Updated Successfully!!";
        }else{
            return "User Not Found!!";
        }
    }

//    Delete User by Id
    public String deleteUserById(String id) {
        Optional<User> op=userRepository.findById(id);
        if(op.isPresent()) {
            userRepository.deleteById(id);
            return "Data Deleted Successfully!!";
        }else{
            return "Data Not Found!!";
        }
    }

//    Delete all the Users
    public String deleteAllUsers() {
        List<User> list=userRepository.findAll();
        if(list.isEmpty()) {
            return "Data Not Found!!";
        }else{
            userRepository.deleteAll();
            return "Data Deleted Successfully!!";
        }
    }
}