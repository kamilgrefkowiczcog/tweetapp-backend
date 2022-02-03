package com.tweetapp.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@AllArgsConstructor
public class AuthResponse {

     String username;
     String jwt;
     Date expiresAt;


}
