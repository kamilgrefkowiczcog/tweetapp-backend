package com.tweetapp.user.controller;

import com.tweetapp.security.JwtTokenUtil;
import com.tweetapp.user.domain.UserEntity;
import com.tweetapp.user.domain.dto.*;
import com.tweetapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {

        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
                    );

            UserEntity user = (UserEntity) authenticate.getPrincipal();

            String jwt = jwtTokenUtil.generateAccessToken(user);
            return ResponseEntity.ok()
                    .body(new AuthResponse(user.getUsername(), jwt, jwtTokenUtil.getExpirationDate(jwt)));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody @Valid RegisterRequest request) {

        return userService.registerNewUser(request);
    }

    @PostMapping("reset-password")
    public ResetPasswordResponse resetPassword(@RequestBody @Valid ResetPasswordRequest request){
        return userService.resetPassword(request);
    }


    @GetMapping("test")
    public String test() {
        return "test";
    }
}
