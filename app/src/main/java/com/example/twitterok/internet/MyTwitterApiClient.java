package com.example.twitterok.internet;

import com.example.twitterok.model.TweetModel;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.network.OkHttpClientHelper;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyTwitterApiClient extends TwitterApiClient {
    private TwitterSession session;


    public MyTwitterApiClient(TwitterSession twitterSession){
        super(twitterSession);
        this.session = twitterSession;
    }

    public Observable<User> getUser(long id){
        return new Retrofit.Builder()
                .client(OkHttpClientHelper.getOkHttpClient(session, TwitterCore.getInstance().getAuthConfig()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.twitter.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RxGetTweetTimeline.class)
                .getUser(id);
    }

    public Observable<List<TweetModel>> getApi(int count){
        return new Retrofit.Builder()
                .client(OkHttpClientHelper.getOkHttpClient(session, TwitterCore.getInstance().getAuthConfig()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.twitter.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RxGetTweetTimeline.class)
                .getTimeline(count);
    }
    public Observable<List<User>> getSearch(String query){
        return new Retrofit.Builder()
                .client(OkHttpClientHelper.getOkHttpClient(session, TwitterCore.getInstance().getAuthConfig()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.twitter.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RxGetTweetTimeline.class)
                .getSearchingUsers(query);
    }

    public Observable<List<TweetModel>> getHomeTimeline(int count, String nickname){
        return new Retrofit.Builder()
                .client(OkHttpClientHelper.getOkHttpClient(session, TwitterCore.getInstance().getAuthConfig()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.twitter.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RxGetTweetTimeline.class)
                .getHomeTimeline(count,nickname);
    }
}
