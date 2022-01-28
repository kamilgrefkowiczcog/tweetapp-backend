package com.tweetapp.user.controller;

import com.tweetapp.security.JwtTokenUtil;
import com.tweetapp.user.domain.UserEntity;
import com.tweetapp.user.domain.dto.AuthRequest;
import com.tweetapp.user.domain.dto.RegisterCommand;
import com.tweetapp.user.domain.dto.UserDto;
import com.tweetapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid AuthRequest request) {

        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
                    );

            UserEntity user = (UserEntity) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateAccessToken(user)
                    )
                    .body(new UserDto(user.getUsername()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody @Valid RegisterCommand command) {

        return userService.registerNewUser(command);
    }
}
