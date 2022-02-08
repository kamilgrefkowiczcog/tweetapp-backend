package com.tweetapp.tweet.domain.dto;

import lombok.Value;

import java.util.Date;

@Value
public class CommentDto {

    String text;
    String authorDisplayName;
    Date createdDate;
}
