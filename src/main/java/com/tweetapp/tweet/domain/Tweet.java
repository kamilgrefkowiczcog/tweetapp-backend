package com.tweetapp.tweet.domain;

import com.tweetapp.user.domain.UserEntity;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.web.SortDefault;

import java.util.*;

@Document
@Data
public class Tweet {

    @Id
    private String id;

    private String text;

    @DocumentReference(lazy = true)
    private UserEntity author;

    @DocumentReference(lazy = true)
    private Set<UserEntity> likedBy = new HashSet<>();

    @CreatedDate
    private Date createdDate;

    @DocumentReference(lazy = true)
    private Set<Comment> comments = new HashSet<>();

    public void like(UserEntity user) {
        likedBy.add(user);
        user.getLikedTweets().add(this);
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
        author.getAuthoredTweets().add(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setTweet(this);
    }
}
