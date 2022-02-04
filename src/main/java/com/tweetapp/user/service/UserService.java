package com.tweetapp.user.service;

import com.tweetapp.user.domain.UserEntity;
import com.tweetapp.user.domain.dto.RegisterRequest;
import com.tweetapp.user.domain.dto.RegisterResponse;
import com.tweetapp.user.domain.dto.ResetPasswordRequest;
import com.tweetapp.user.domain.dto.ResetPasswordResponse;
import com.tweetapp.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Random random = new Random();

    @Transactional
    public RegisterResponse registerNewUser(RegisterRequest command) {

        if (userRepository.findByUsername(command.getUsername()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (!command.getPassword().equals(command.getRePassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        UserEntity user = createUser(command);

        return new RegisterResponse(user.getUsername());
    }

    private UserEntity createUser(RegisterRequest command) {
        UserEntity user = new UserEntity();
        user.setUsername(command.getUsername());
        user.setDisplayName(command.getDisplayName());
        user.setPassword(passwordEncoder.encode(command.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {

        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("No user registered on this email"));

        String newPassword = generateRandomString();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return new ResetPasswordResponse(newPassword);
    }

    private String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;


        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
}
