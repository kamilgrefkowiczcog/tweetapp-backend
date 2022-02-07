package com.tweetapp.user.controller;

import com.tweetapp.user.domain.dto.FullUserDto;
import com.tweetapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/current")
    public FullUserDto getCurrentUser(Principal principal) {

        return userService.getCurrentUser(principal.getName());
    }
}
