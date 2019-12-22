package com.example.twitterok.repository.internet.services;

import com.example.twitterok.model.TweetModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TimelineService {

    @GET("/1.1/statuses/home_timeline.json?tweet_mode=extended")
    Observable<List<TweetModel>> getHomeTimeline(@Query("count") int count);

    @GET("/1.1/statuses/user_timeline.json?tweet_mode=extended")
    Observable<List<TweetModel>> getUserTimeline(@Query("count") int count, @Query("screen_name") String screenName);

}
