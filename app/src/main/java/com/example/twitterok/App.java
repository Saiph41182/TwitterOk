package com.example.twitterok;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import com.example.twitterok.databinding.AppDataBindingComponent;
import com.example.twitterok.internet.MyTwitterApiClient;
import com.example.twitterok.realm.RealmAccountModel;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static TwitterSession session;
    private static RealmAccountModel owner;

    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
        initRealm();
        initTwitterCore(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET));
    }
    private void initRealm(){
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration
                .Builder()
                .name("accounts.realm")
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    private void initTwitterCore(String key,String secret) {
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(key,secret))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    public static TwitterSession getSession(){
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }

    public static void setSession(TwitterSession twitterSession){
        TwitterCore.getInstance().getSessionManager().setActiveSession(twitterSession);
    }

    public static RealmAccountModel getOwner() {
        return owner;
    }

    public static void setOwner(RealmAccountModel owner) {
        App.owner = owner;
    }
}
/* private static void initOwner(){
         Disposable disposable = new MyTwitterApiClient(session)
                 .getUser(session.getUserId())
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(user -> {
                     owner = user;
                     Log.d("MyApp", "initOwner: owner downloaded: "+ owner);
                 });
         Log.d("MyApp", "initOwner: end method");
     }*/