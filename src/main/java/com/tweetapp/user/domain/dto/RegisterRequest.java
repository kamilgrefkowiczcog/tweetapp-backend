package com.tweetapp.user.domain.dto;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
public class RegisterRequest {

    @NotBlank
    @Email
    String username;
    @NotBlank
    String displayName;
    @NotBlank
    String password;
    @NotBlank
    String rePassword;
}
