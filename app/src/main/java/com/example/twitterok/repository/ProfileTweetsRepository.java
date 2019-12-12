package com.example.twitterok.repository;

import com.example.twitterok.App;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.repository.internet.TwitterApiProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileTweetsRepository {

    public Observable<List<TweetModel>> getData(String screenName){
        TwitterApiProvider twitterApiClient = new TwitterApiProvider(App.getSession());
        return twitterApiClient
                .getUserTimeline(20,screenName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
