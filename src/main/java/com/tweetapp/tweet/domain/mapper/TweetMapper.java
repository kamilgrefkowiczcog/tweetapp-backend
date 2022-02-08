package com.tweetapp.tweet.domain.mapper;

import com.tweetapp.tweet.domain.Comment;
import com.tweetapp.tweet.domain.Tweet;
import com.tweetapp.tweet.domain.dto.CommentDto;
import com.tweetapp.tweet.domain.dto.TweetDto;
import com.tweetapp.user.domain.UserEntity;

public class TweetMapper {

    public static TweetDto tweetToDto(Tweet tweet) {
        return new TweetDto(tweet.getId(), tweet.getText(), tweet.getCreatedDate(), tweet.getAuthor().getId(), tweet.getAuthor().getDisplayName(), tweet.getLikedBy().stream().map(UserEntity::getId).toList(), tweet.getComments().stream().map(TweetMapper::commentToDto).toList());
    }

    public static CommentDto commentToDto (Comment comment) {
        return new CommentDto(comment.getText(), comment.getAuthor().getDisplayName(), comment.getCreatedDate());
    }
}
