package com.tweetapp.tweet.domain;


import com.tweetapp.user.domain.UserEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Document
@Data
public class Comment {

    @Id
    private String id;

    private String text;

    @DocumentReference(lazy = true)
    private Tweet tweet;

    @DocumentReference(lazy = true)
    private UserEntity author;

    @CreatedDate
    private Date createdDate;
}
