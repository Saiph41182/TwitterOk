package com.example.twitterok.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.twitterok.App;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.repository.internet.TwitterApiProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TweetsRepository {

    public Observable<List<TweetModel>> getData(){
        TwitterApiProvider twitterApiClient = new TwitterApiProvider(App.getSession());
      return twitterApiClient
                .getHomeTimeline(20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
