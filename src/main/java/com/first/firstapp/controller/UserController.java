package com.first.firstapp.controller;

import com.first.firstapp.controller.dto.UserRequest;
import com.first.firstapp.controller.dto.UserResponse;
import com.first.firstapp.exception.UserNotFoundException;
import com.first.firstapp.models.User;
import com.first.firstapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User  userAdd(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        userRepository.save(user);
        return user;
    }

    @GetMapping("/user/{userid}")
    public User userDetails(@PathVariable(value = "userid") long id) {
        return  userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{userid}")
    public ResponseEntity<UserResponse> userUpdate(@PathVariable(value = "userid") long id,
                             @RequestBody UserRequest userRequest) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (user!=null) {
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setPhone(userRequest.getPhone());
            userRepository.save(user);
        }
        return new ResponseEntity<>(new UserResponse(0, "Пользователь обновлен"),OK);
    }

    @DeleteMapping("/user/{userid}")
    public ResponseEntity<UserResponse> userDelete(@PathVariable(value = "userid") long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(new UserResponse(0, "Пользователь удален"),NO_CONTENT);
    }
}
