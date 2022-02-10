package com.tweetapp.user.domain.dto;

import lombok.Value;

@Value
public class PoorUserDto {

    String displayName;
    Integer authoredTweetsNumber;
}
