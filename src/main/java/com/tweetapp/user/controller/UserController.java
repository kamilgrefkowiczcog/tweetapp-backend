package com.tweetapp.user.controller;

import com.tweetapp.user.domain.dto.FullUserDto;
import com.tweetapp.user.domain.dto.PoorUserDto;
import com.tweetapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/current")
    public FullUserDto getCurrentUser(Principal principal) {

        return userService.getCurrentUser(principal.getName());
    }

    @GetMapping
    public List<PoorUserDto> getAllUsers(@RequestParam Optional<String> partialUsername) {

        if (partialUsername.isEmpty()) {
            return userService.getAllUsers();

        } else {

            return userService.findByPartialUsername(partialUsername.get());
        }
    }


}
