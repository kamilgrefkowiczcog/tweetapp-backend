package com.tweetapp.user.repository;

import com.tweetapp.tweet.domain.dto.TweetDto;
import com.tweetapp.user.domain.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByDisplayName(String displayName);
}
