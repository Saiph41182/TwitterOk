package com.example.twitterok.databinding.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.twitterok.Utils.TwitterDateParser;
import com.example.twitterok.model.TweetModel;

public class TweetViewModel extends BaseObservable {

    private TweetModel tweetModel;

    public TweetViewModel(TweetModel tweetModel) {
        this.tweetModel = tweetModel;
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

    @Bindable public String getDiscription(){
        return tweetModel.text != null ? tweetModel.text : "Today_I_have_gonna_show_you_a_small_pines_and_a_large_vagina_with_a_toys_into";
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
        if (tweetModel.extendedEntities == null) return null;
        return !((tweetModel.extendedEntities.media == null) || (tweetModel.extendedEntities.media.isEmpty()))
                ? tweetModel.extendedEntities.media.get(0).mediaUrlHttps
                : null;
    }

    public void clickOnTweet(View view){
        temp(view.getContext(),"onTweetClicked");
    }
    public void clickOnLike(View view){
        temp(view.getContext(),"onLikeClicked");
    }
    public void clickOnProfileImage(View view){
        temp(view.getContext(), "onProfileImageClicked");
    }
    private void temp(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
