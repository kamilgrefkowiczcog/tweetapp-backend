package com.tweetapp.user.domain.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Value
//below annotation fixes some weird jackson deserialization bug -- do not remove
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ResetPasswordRequest {

    @Email
    @NotNull
    String username;


}
