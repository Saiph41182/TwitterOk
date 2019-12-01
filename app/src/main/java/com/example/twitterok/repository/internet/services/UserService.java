package com.example.twitterok.repository.internet.services;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("/1.1/users/show.json")
    Observable<com.twitter.sdk.android.core.models.User> getUser(@Query("user_id") long id);

}
