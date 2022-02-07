package com.tweetapp.tweet.service;

import com.tweetapp.tweet.domain.Tweet;
import com.tweetapp.tweet.domain.TweetMapper;
import com.tweetapp.tweet.domain.dto.NewTweetRequest;
import com.tweetapp.tweet.domain.dto.TweetDto;
import com.tweetapp.tweet.repository.TweetRepository;
import com.tweetapp.user.domain.UserEntity;
import com.tweetapp.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserEntityRepository userEntityRepository;

    @Transactional
    public TweetDto createTweet(NewTweetRequest request, String username) {

        UserEntity user = userEntityRepository.findByUsername(username).orElseThrow();
        Tweet tweet = mapToTweet(request, user);
        tweetRepository.save(tweet);
        userEntityRepository.save(user);
        return TweetMapper.mapToDto(tweet);
    }

    @Transactional
    public List<TweetDto> getAllTweets() {
        return tweetRepository.findAll(Sort.by("createdDate").descending())
                .stream().map(TweetMapper::mapToDto)
                .toList();
    }

    private Tweet mapToTweet(NewTweetRequest request, UserEntity user) {
        Tweet tweet = new Tweet();
        tweet.setAuthor(user);
        tweet.setText(request.getText());
        return tweet;
    }



    @Transactional
    public TweetDto likeTweet(String tweetId, String username) {

        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow();
        UserEntity user = userEntityRepository.findByUsername(username).orElseThrow();
        tweet.like(user);

        tweetRepository.save(tweet);
        userEntityRepository.save(user);
        return TweetMapper.mapToDto(tweet);

    }
}
