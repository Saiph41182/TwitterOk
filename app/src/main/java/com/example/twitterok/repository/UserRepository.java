package com.example.twitterok.repository;

import com.example.twitterok.App;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.repository.internet.TwitterApiProvider;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {

    public Observable<User> getData(String screenName){
        TwitterApiProvider twitterApiClient = new TwitterApiProvider(App.getSession());
        return twitterApiClient
                .getUser(screenName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
