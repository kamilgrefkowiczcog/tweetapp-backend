package com.tweetapp.tweet.domain.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
//below annotation fixes some weird jackson deserialization bug -- do not remove
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class NewTweetRequest {

    String text;
}
