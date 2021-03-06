package com.tweetapp.tweet.controller;

import com.tweetapp.tweet.domain.dto.CommentRequest;
import com.tweetapp.tweet.domain.dto.NewTweetRequest;
import com.tweetapp.tweet.domain.dto.TweetDto;
import com.tweetapp.tweet.service.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{displayName}")
    public List<TweetDto> getAllTweetsByDisplayName(@PathVariable String displayName) {
        return tweetService.getTweetsByUser(displayName);
    }


    @PostMapping("/{tweetId}/like")
    public TweetDto likeTweet(@PathVariable String tweetId, Principal principal) {

        return tweetService.likeTweet(tweetId, principal.getName());
    }

    @PostMapping("/{tweetId}/comment")
    public TweetDto commentTweet(@PathVariable String tweetId, @Valid @RequestBody CommentRequest request, Principal principal) {


        return tweetService.commentTweet(tweetId, request, principal.getName());
    }
    @DeleteMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTweet(@PathVariable String tweetId, Principal principal) {
        this.tweetService.deleteTweet(tweetId, principal.getName());
    }
}
