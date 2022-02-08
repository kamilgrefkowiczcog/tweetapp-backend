package com.tweetapp.tweet.repository;

import com.tweetapp.tweet.domain.Tweet;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

public interface TweetRepository extends MongoRepository<Tweet, String> {

}
