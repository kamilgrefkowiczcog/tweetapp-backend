package com.tweetapp.tweet.domain.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Value
//below annotation fixes some weird jackson deserialization bug -- do not remove
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CommentRequest {

    @Length(max = 144)
    @NotBlank
    String text;
}
