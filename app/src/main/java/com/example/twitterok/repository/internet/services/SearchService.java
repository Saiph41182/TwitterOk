package com.example.twitterok.repository.internet.services;

import com.twitter.sdk.android.core.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("/1.1/users/search.json")
    Observable<List<User>> getSearchingUsers(@Query("q") String q);

}
