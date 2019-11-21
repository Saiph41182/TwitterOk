package com.example.twitterok.realm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import java.io.ByteArrayOutputStream;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class RealmAccountModel extends RealmObject {

    @PrimaryKey private long id;

    @Required private String name;

    @Required private String screenName;

    @Required private String createdAt;

    @Required private String description;

    @Required private String followersCount;

    @Required private String followingCount;

    @Required private String authToken;

    @Required private String authSecret;

    @Required private byte[] bitmapProfileImage;

    public RealmAccountModel(User user, TwitterAuthToken authToken,Bitmap bitmapProfileImage) {
        this.id = user.id;
        this.name = user.name;
        this.screenName = user.screenName;
        this.createdAt = user.createdAt;
        this.description = user.description;
        this.followersCount = String.valueOf(user.followersCount);
        this.followingCount = String.valueOf(user.favouritesCount);
        this.authToken = authToken.token;
        this.authSecret = authToken.secret;
        this.setBitmapProfileImage(bitmapProfileImage);
    }

    public RealmAccountModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    public String getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(String followingCount) {
        this.followingCount = followingCount;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthSecret() {
        return authSecret;
    }

    public void setAuthSecret(String authSecret) {
        this.authSecret = authSecret;
    }

    public Bitmap getBitmapProfileImage() {
        return BitmapFactory.decodeByteArray(bitmapProfileImage,0,bitmapProfileImage.length);
    }

    public void setBitmapProfileImage(Bitmap bitmapProfileImage) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapProfileImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
        this.bitmapProfileImage = stream.toByteArray();
    }

    @NonNull
    public TwitterSession getSession(){
        return new TwitterSession(new TwitterAuthToken(authToken,authSecret),id,name);
    }
}
