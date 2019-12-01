package com.example.twitterok.repository.internet;

import com.example.twitterok.repository.internet.services.SearchService;
import com.example.twitterok.repository.internet.services.TimelineService;
import com.example.twitterok.repository.internet.services.UserService;
import com.example.twitterok.model.TweetModel;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.network.OkHttpClientHelper;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwitterApiProvider {

    private static final String BASE_URL = "https://api.twitter.com";

    private TwitterSession session;
    private Retrofit retrofit;
    private OkHttpClient client;

    public TwitterApiProvider(TwitterSession twitterSession){
        this.session = twitterSession;
        client = buildClient();
        retrofit = buildRetrofit();

    }

    private Retrofit buildRetrofit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build();
    }

    private OkHttpClient buildClient(){
        return OkHttpClientHelper.getOkHttpClient(session, TwitterCore.getInstance().getAuthConfig());
    }

    public Observable<User> getUser(long id){
        return retrofit
                .create(UserService.class)
                .getUser(id);
    }

    public Observable<List<TweetModel>> getHomeTimeline(int count){
        return retrofit
                .create(TimelineService.class)
                .getHomeTimeline(count);
    }
    public Observable<List<User>> getSearch(String query){
        return retrofit
                .create(SearchService.class)
                .getSearchingUsers(query);
    }

    public Observable<List<TweetModel>> getUserTimeline(int count, String nickname){
        return retrofit
                .create(TimelineService.class)
                .getUserTimeline(count,nickname);
    }
}
