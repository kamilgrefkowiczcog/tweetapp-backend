package com.tweetapp.user.domain;


import com.tweetapp.tweet.domain.Tweet;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;


@Data
@Document
public class UserEntity implements UserDetails {

    @Id
    private String id;

    private String username;
    private String displayName;
    private String password;

    @DocumentReference
    private Set<Tweet> authoredTweets;

    @DocumentReference(lazy = true)
    private Set<Tweet> likedTweets;



    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
