package com.raxrot.controller;

import com.raxrot.model.User;
import com.raxrot.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/api/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable("id") Long id) throws Exception {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        throw new Exception("User not found");
    }

    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable("id") Long id,@Valid @RequestBody User user) throws Exception {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new Exception("User not found");
        }
        else{
            User existingUser = userOpt.get();
            existingUser.setFullName(user.getFullName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        }
    }

    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) throws Exception {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.deleteById(userOpt.get().getId());
        }else{
            throw new Exception("User not found");
        }
        return "User deleted";
    }

}
