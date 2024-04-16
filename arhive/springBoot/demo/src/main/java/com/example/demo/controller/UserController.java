package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    
//register
    @PostMapping("/register")
public User registerUser(@RequestBody User user) {
    // Simple validation without security approach
    if (user.getEmail() == null || user.getPassword() == null) {
        throw new RuntimeException("Email and password cannot be null");
    }

    // Check if user already exists
    if (userRepository.findByEmail(user.getEmail()) != null) {
        throw new RuntimeException("User with email " + user.getEmail() + " already exists");
    }

    // Directly save the user without encoding the password
    return userRepository.save(user);
}
    

    //update users details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setEmail(userDetails.getEmail());
                    // Add other fields that you allow to be updated
                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }
      //delete user
      @DeleteMapping("/{id}")
      public ResponseEntity<?> deleteUser(@PathVariable String id) {
          return userRepository.findById(id)
                  .map(user -> {
                      userRepository.delete(user);
                      return ResponseEntity.ok().build();
                  })
                  .orElse(ResponseEntity.notFound().build());
      }

}

   



