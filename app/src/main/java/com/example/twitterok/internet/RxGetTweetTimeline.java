package com.example.twitterok.internet;

import android.graphics.Bitmap;

import com.example.twitterok.model.TweetModel;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RxGetTweetTimeline {

    @GET("/1.1/statuses/home_timeline.json")
    Observable<List<TweetModel>> getTimeline(@Query("count") int count);

    @GET("/1.1/users/show.json")
    Observable<User> getUser(@Query("user_id") long id);

    @GET("/1.1/users/search.json")
    Observable<List<User>> getSearchingUsers(@Query("q") String q);

    @GET("/1.1/statuses/user_timeline.json")
    Observable<List<TweetModel>> getHomeTimeline(@Query("count") int count, @Query("screen_name") String screenName);
}
