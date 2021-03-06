package com.tweetapp.tweet.repository;

import com.tweetapp.tweet.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
