package com.tweetapp.user.domain.dto;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
public class RegisterCommand {

    @NotBlank
    @Email
    String email;
    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank
    String rePassword;
}
