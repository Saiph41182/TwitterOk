package com.example.twitterok.databinding.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.twitterok.Utils.TwitterDateParser;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.view.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.TwitterApiClient;

public class TweetViewModel extends BaseObservable {

    private TweetModel tweetModel;
    private BaseFragment.ViewModelClickListener clickListener;

    public TweetViewModel(TweetModel tweetModel, BaseFragment.ViewModelClickListener clickListener) {
        this.tweetModel = tweetModel;
        this.clickListener = clickListener;
    }

    @Bindable public String getAuthor(){
        return tweetModel.user.name != null ? tweetModel.user.name : "author";
    }

    @Bindable public String getNickname(){
        return tweetModel.user.screenName != null ? "@".concat(tweetModel.user.screenName) : "@nickname";
    }

    @Bindable public String getDate(){
        TwitterDateParser dateParser = new TwitterDateParser();
        dateParser.setDateJson(tweetModel.createdAt);
        return dateParser.wasPosted();
    }

    @Bindable public String getText(){
        return tweetModel.text == null ? null : tweetModel.text.replaceAll("(?i)\\b(" +
                "(?:[a-z][\\w-]+:(?:/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)" +
                "(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|" +
                "(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»\"\"‘’]))","").trim();
    }

    @Bindable public String getLikeCount(){
        return tweetModel.favoriteCount != null ? String.valueOf(tweetModel.favoriteCount) : "0";
    }

    @Bindable public String getRetweetCount(){
        return tweetModel.retweetCount < 0 ? String.valueOf(tweetModel.retweetCount) : "0";
    }

    @Bindable public String getFollowingCount(){
        return "-0";
    }

    @Bindable public String getFollowersCount(){
        return "-0";
    }

    @Bindable public String getLocation(){
        return tweetModel.user.location != null ? tweetModel.user.location : "location";
    }

    @Bindable public String getProfileImageUrl(){
        return tweetModel.user.profileImageUrlHttps.replace("_normal","_bigger");
    }

    @Bindable public String getMediaEntitiesUrl(){
        return (tweetModel.extendedEntities == null
                || tweetModel.extendedEntities.media == null
                || tweetModel.extendedEntities.media.isEmpty())
                ? null : tweetModel.extendedEntities.media.get(0).mediaUrlHttps;
    }

    @Bindable public String getUrlFieldContent(){
        return  (tweetModel.entities == null
                || tweetModel.entities.urls == null
                || tweetModel.entities.urls.isEmpty())
                ? null : tweetModel.entities.urls.get(0).expandedUrl;
    }

    public String getStringId(){
        return tweetModel.idStr;
    }

    public String getUserJson(){
        return new GsonBuilder().create().toJson(tweetModel.user);
    }

    public void onClick(View view,String text){
        clickListener.onClick(view,text);
    }

}
