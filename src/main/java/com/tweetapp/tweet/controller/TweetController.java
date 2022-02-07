package com.tweetapp.tweet.controller;

import com.tweetapp.tweet.domain.dto.NewTweetRequest;
import com.tweetapp.tweet.domain.dto.TweetDto;
import com.tweetapp.tweet.service.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tweets")
@RequiredArgsConstructor
@Slf4j
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    public TweetDto postTweet(@RequestBody @Valid NewTweetRequest request, Principal principal) {
        return tweetService.createTweet(request, principal.getName());
    }

    @GetMapping
    public List<TweetDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @PostMapping("/like/{tweetId}")
    public TweetDto likeTweet(@PathVariable String tweetId, Principal principal) {

        return tweetService.likeTweet(tweetId, principal.getName());
    }
}
