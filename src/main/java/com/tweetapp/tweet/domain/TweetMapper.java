package com.tweetapp.tweet.domain;

import com.tweetapp.tweet.domain.dto.TweetDto;

public class TweetMapper {

    public static TweetDto mapToDto(Tweet tweet) {
        return new TweetDto(tweet.getId(), tweet.getText(), tweet.getCreatedDate(), tweet.getAuthor().getId(), tweet.getAuthor().getDisplayName(), tweet.getLikedBy().size());
    }
}
