package com.tweetapp.tweet.domain.dto;

import lombok.Value;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Value
public class TweetDto {

    String id;
    String text;
    Date createdAt;

    String authorId;
    String authorDisplayName;
    Set<String> likedBy;
    List<CommentDto> comments;
}
