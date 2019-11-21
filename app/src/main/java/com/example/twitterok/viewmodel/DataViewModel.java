package com.example.twitterok.viewmodel;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.adapters.AnimatedMainAdapter;
import com.example.twitterok.adapters.MainAdapter;
import com.example.twitterok.databinding.ItemTweetViewBinding;
import com.example.twitterok.databinding.ItemUserBinding;
import com.example.twitterok.internet.MyTwitterApiClient;
import com.example.twitterok.model.TweetModel;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataViewModel extends BaseObservable {
    public static final String TAG = "DataViewModel";


    private List<TweetModel> data;
    private Disposable disposable;
    private MainAdapter mainAdapter;

    public DataViewModel() {
        data = new ArrayList<>();
        mainAdapter = new AnimatedMainAdapter(R.layout.item_tweet_view);
    }

    public void setUp(){
        populateData();
    }

    public void tearDown(){
        destroyData();
    }

    private void destroyData() {
        disposable.dispose();
        data.clear();
    }

    private void populateData() {
        MyTwitterApiClient twitterApiClient = new MyTwitterApiClient(App.getSession());
        disposable = twitterApiClient
                .getApi(200)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result -> data.addAll(result))
                .subscribe(result -> notifyPropertyChanged(BR.data));
    }

    @Bindable
    public List<TweetModel> getData(){
        return data;
    }

    @Bindable
    public MainAdapter getAnimAdapter(){
        return mainAdapter;
    }

}
