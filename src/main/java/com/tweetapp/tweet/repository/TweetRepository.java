package com.tweetapp.tweet.repository;

import com.tweetapp.tweet.domain.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TweetRepository extends MongoRepository<Tweet, String> {
}
