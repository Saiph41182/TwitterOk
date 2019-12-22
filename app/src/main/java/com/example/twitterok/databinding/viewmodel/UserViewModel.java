package com.example.twitterok.databinding.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.twitterok.Utils.TwitterDateParser;
import com.twitter.sdk.android.core.models.User;

public class UserViewModel extends BaseObservable {

    private User user;

    public UserViewModel(User user) {
        this.user = user;
    }

    @Bindable
    public String getImageUrl() {
        return user.profileImageUrlHttps.replace("_normal", "_bigger");
    }

    @Bindable
    public String getName() {
        return user.name;
    }

    @Bindable
    public String getNickname() {
        return "@".concat(user.screenName);
    }

    @Bindable
    public String getFollowingCount() {
        return String.valueOf(user.friendsCount);
    }

    @Bindable
    public String getFollowersCount() {
        return String.valueOf(user.followersCount);
    }

    @Bindable
    public String getDescription() {
        return user.description;
    }

    @Bindable
    public String getDate() {
        TwitterDateParser dateParser = new TwitterDateParser();
        dateParser.setDateJson(user.createdAt);
        return dateParser.wasCreated();
    }

    @Bindable
    public String getCreatedAt(){
        return user.createdAt;
    }
}