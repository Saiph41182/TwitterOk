package com.example.twitterok.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ViewDataBinding;

import com.example.twitterok.App;
import com.example.twitterok.Utils.TwitterDateParser;
import com.example.twitterok.realm.RealmAccountModel;
import com.example.twitterok.view.MainActivity;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import io.realm.Realm;

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


}
