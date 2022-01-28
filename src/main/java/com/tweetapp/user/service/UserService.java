package com.tweetapp.user.service;

import com.tweetapp.user.domain.UserEntity;
import com.tweetapp.user.domain.dto.RegisterCommand;
import com.tweetapp.user.domain.dto.UserDto;
import com.tweetapp.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto registerNewUser(RegisterCommand command) {

        if (userRepository.findByEmail(command.getEmail()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (!command.getPassword().equals(command.getRePassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        UserEntity user = createUser(command);

        return new UserDto(user.getUsername());
    }

    private UserEntity createUser(RegisterCommand command) {
        UserEntity user = new UserEntity();
        user.setUsername(command.getUsername());
        user.setEmail(command.getEmail());
        user.setPassword(passwordEncoder.encode(command.getPassword()));
        user = userRepository.save(user);
        return user;
    }
}
