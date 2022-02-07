package com.tweetapp.tweet.domain.dto;

import lombok.Value;

import java.util.Date;

@Value
public class TweetDto {

    String id;
    String text;
    Date createdAt;

    String authorId;
    String authorDisplayName;
    Integer likes;
}
