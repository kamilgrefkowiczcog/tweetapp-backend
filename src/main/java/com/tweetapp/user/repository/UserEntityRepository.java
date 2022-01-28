package com.tweetapp.user.repository;

import com.tweetapp.user.domain.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserEntityRepository extends MongoRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
