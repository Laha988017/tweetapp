package com.tweetapp.controller;

import com.tweetapp.exception.TweetAppException;
import com.tweetapp.model.utilityModel.ApiResponse;
import com.tweetapp.model.utilityModel.ChangePassword;
import com.tweetapp.model.utilityModel.LoginModel;
import com.tweetapp.model.Users;
import com.tweetapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1.0/tweets")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody Users users) throws TweetAppException {
        Users createdUser = userService.createUser(users);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{loginId}").buildAndExpand(createdUser.getId()).toUri();
        log.info("User created successfully");
        return ResponseEntity.created(uri).body(ApiResponse.builder().status(201).message("User registered successfully").data(createdUser.getUsername()).build());
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginModel users) throws TweetAppException {
        Users u = userService.login(users);
        if(u!=null)
            return ResponseEntity.ok(ApiResponse.builder()
                            .status(200)
                            .message("Login successful")
                            .data(u)
                    .build());
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                        .status(400).message("Login unsuccessful")
                .build());
    }

    @GetMapping("/{username}/forgot")
    public ResponseEntity<ApiResponse> changePassword(@PathVariable String username, @RequestBody ChangePassword cp) throws TweetAppException {
        Users u = userService.updatePassword(cp,username);
        return ResponseEntity.ok().body(ApiResponse.builder()
                .status(200).message("Password reset successful").data(u)
                .build());
    }

    @GetMapping("/user/search/{username}")
    public ResponseEntity<ApiResponse> searchByUsername(@PathVariable String username) throws TweetAppException {
        List<Users> usersList = userService.getUserByRegex(username);
        return ResponseEntity.ok().body(ApiResponse.builder()
                .status(200).message("Users Found").data(usersList)
                .build());
    }

    @GetMapping("users/all")
    public ResponseEntity<ApiResponse> getAllUsers(){
        return ResponseEntity.ok().body(ApiResponse.builder()
                .status(200).message("Users Found").data(userService.getAllUsers())
                .build());
    }
}
