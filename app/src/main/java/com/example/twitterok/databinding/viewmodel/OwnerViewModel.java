package com.example.twitterok.databinding.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.twitterok.App;
import com.example.twitterok.Utils.TwitterDateParser;
import com.example.twitterok.repository.realm.RealmAccountModel;
import com.example.twitterok.view.MainActivity;
import com.twitter.sdk.android.core.TwitterSession;

import io.realm.Realm;

public class OwnerViewModel extends BaseObservable {

    private RealmAccountModel model;

    public OwnerViewModel(RealmAccountModel model) {
        this.model = model;
    }

    @Bindable
    public long getId(){
        return model.getId();
    }

    @Bindable
    public String getName(){
        return model.getName();
    }

    @Bindable
    public String getScreenName(){
        return "@".concat(model.getScreenName());
    }

    @Bindable
    public String getDescription(){
        return model.getDescription();
    }

    @Bindable
     public String getCreatedAt(){
        TwitterDateParser dateParser = new TwitterDateParser();
        dateParser.setDateJson(model.getCreatedAt());
        return dateParser.wasCreated();
    }

    @Bindable
    public String getFollowersCount(){
        return model.getFollowersCount();
    }

    @Bindable
    public String getFollowingCount(){
        return model.getFollowingCount();
    }

    @Bindable
    public Bitmap getImageBitmap(){
        return model.getBitmapProfileImage();
    }

    public void onClick(View view){
        Context context = view.getContext();
        TwitterSession twitterSession = Realm.getDefaultInstance()
                .where(RealmAccountModel.class)
                .equalTo("id",model.getId())
                .findFirst()
                .getSession();
        App.setOwner(model);
        App.setSession(twitterSession);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
