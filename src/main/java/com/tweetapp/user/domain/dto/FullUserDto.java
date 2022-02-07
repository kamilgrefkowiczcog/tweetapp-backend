package com.tweetapp.user.domain.dto;

import com.tweetapp.tweet.domain.dto.TweetDto;
import lombok.Value;

import java.util.Set;

@Value
public class FullUserDto {

    String username;
    String displayName;
    Set<String> likedTweets;
    Set<TweetDto> authoredTweets;
}
