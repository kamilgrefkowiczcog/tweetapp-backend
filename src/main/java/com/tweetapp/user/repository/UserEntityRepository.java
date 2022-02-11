package com.tweetapp.user.repository;

import com.tweetapp.tweet.domain.dto.TweetDto;
import com.tweetapp.user.domain.UserEntity;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends MongoRepository<UserEntity, String>  {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByDisplayName(String displayName);

    @Aggregation(pipeline = { "{$match: {'displayName': {$regex : ?0}}}", "{$addFields : {numberOfTweets : {$size : { $ifNull : [$authoredTweets, []]}}}}", "{$sort : {numberOfTweets : -1}}"})
    List<UserEntity> findAllByPartialUsername(String partialUsername);

    @Aggregation(pipeline = {"{$addFields : {numberOfTweets : {$size : { $ifNull : [$authoredTweets, []]}}}}", "{$sort : {numberOfTweets : -1}}"})
    List<UserEntity> findAll();

}
