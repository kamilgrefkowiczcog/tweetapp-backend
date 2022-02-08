package com.tweetapp.tweet.domain;

import com.tweetapp.tweet.domain.dto.TweetDto;
import com.tweetapp.user.domain.UserEntity;

public class TweetMapper {

    public static TweetDto mapToDto(Tweet tweet) {
        return new TweetDto(tweet.getId(), tweet.getText(), tweet.getCreatedDate(), tweet.getAuthor().getId(), tweet.getAuthor().getDisplayName(), tweet.getLikedBy().stream().map(UserEntity::getId).toList());
    }
}
