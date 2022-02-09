package com.tweetapp.user.domain.mapper;

import com.tweetapp.tweet.domain.mapper.TweetMapper;
import com.tweetapp.user.domain.UserEntity;
import com.tweetapp.user.domain.dto.FullUserDto;

import java.util.stream.Collectors;

public class UserMapper {

    public static FullUserDto mapToFullUserDto(UserEntity user) {
        return new FullUserDto(user.getId(), user.getUsername(), user.getDisplayName(), user.getAuthoredTweets().stream().map(TweetMapper::tweetToDto).collect(Collectors.toSet()));
    }
}
