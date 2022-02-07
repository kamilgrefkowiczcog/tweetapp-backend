package com.tweetapp.tweet.service;

import com.tweetapp.tweet.domain.Tweet;
import com.tweetapp.tweet.domain.dto.NewTweetRequest;
import com.tweetapp.tweet.domain.dto.TweetDto;
import com.tweetapp.tweet.repository.TweetRepository;
import com.tweetapp.user.domain.UserEntity;
import com.tweetapp.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserEntityRepository userEntityRepository;

    @Transactional
    public TweetDto createTweet(NewTweetRequest request, String username) {

        Tweet tweet = new Tweet();
        UserEntity author = userEntityRepository.findByUsername(username).orElseThrow();
        tweet.setAuthor(author);
        tweet.setText(request.getText());

        Tweet saved = tweetRepository.save(tweet);

        return new TweetDto(saved.getId(), saved.getText(), saved.getCreatedDate(), author.getId(), author.getDisplayName());
    }


}
