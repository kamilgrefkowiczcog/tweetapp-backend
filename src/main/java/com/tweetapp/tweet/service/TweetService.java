package com.tweetapp.tweet.service;

import com.tweetapp.tweet.domain.Tweet;
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

        Tweet tweet = mapToTweet(request, username);
        tweetRepository.save(tweet);
        return mapToDto(tweet);
    }

    @Transactional
    public List<TweetDto> getAllTweets() {
        return tweetRepository.findAll(Sort.by("createdDate").descending())
                .stream().map(this::mapToDto)
                .toList();
    }

    private Tweet mapToTweet(NewTweetRequest request, String username) {
        Tweet tweet = new Tweet();
        tweet.setAuthor(userEntityRepository.findByUsername(username).orElseThrow());
        tweet.setText(request.getText());
        return tweet;
    }

    private TweetDto mapToDto(Tweet tweet) {
        return new TweetDto(tweet.getId(), tweet.getText(), tweet.getCreatedDate(), tweet.getAuthor().getId(), tweet.getAuthor().getDisplayName(), tweet.getLikedBy().size());
    }

    @Transactional
    public TweetDto likeTweet(String tweetId, String username) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow();

        tweet.like(userEntityRepository.findByUsername(username).orElseThrow());

        return mapToDto(tweetRepository.save(tweet));

    }
}
