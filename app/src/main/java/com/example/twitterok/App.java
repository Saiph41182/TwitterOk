package com.example.twitterok;

import android.app.Application;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import com.example.twitterok.repository.realm.RealmAccountModel;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static TwitterSession session;
    private static RealmAccountModel owner;

    @Override
    public void onCreate() {
        super.onCreate();
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