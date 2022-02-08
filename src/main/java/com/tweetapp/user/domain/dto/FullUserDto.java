package com.tweetapp.user.domain.dto;

import com.tweetapp.tweet.domain.dto.TweetDto;
import lombok.Value;

import java.util.Set;

@Value
public class FullUserDto {

    String userId;
    String username;
    String displayName;
    Set<TweetDto> authoredTweets;
}
