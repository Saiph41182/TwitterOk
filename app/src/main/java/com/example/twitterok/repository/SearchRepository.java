package com.example.twitterok.repository;

import com.example.twitterok.App;
import com.example.twitterok.repository.internet.TwitterApiProvider;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchRepository {

    public Observable<List<User>> getData(String search){
        TwitterApiProvider twitterApiClient = new TwitterApiProvider(App.getSession());
        return twitterApiClient
                .getSearch(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
