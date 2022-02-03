package com.tweetapp.tweet.domain;

import com.tweetapp.user.domain.UserEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
public class Tweet {

    @Id
    private String id = UUID.randomUUID().toString();

    private String text;
    private UserEntity author;
}
