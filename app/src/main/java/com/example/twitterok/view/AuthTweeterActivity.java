package com.example.twitterok.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.twitterok.R;
import com.example.twitterok.view.adapters.AuthAdapter;
import com.example.twitterok.repository.internet.TwitterApiProvider;
import com.example.twitterok.repository.realm.RealmAccountModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class AuthTweeterActivity extends AppCompatActivity {

    public static final String TAG = "AuthTweeterActivity";

    @BindView(R.id.twitter_login_button)
    FloatingActionButton loginButton;

    @BindView(R.id.authorized_users)
    RecyclerView recyclerView;

    private AuthAdapter adapter;
    private TwitterAuthClient authClient;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_tweeter);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        authClient = new TwitterAuthClient();
        initRecyclerView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        authClient.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.twitter_login_button)
    public void doAuthorize(View view){
        authClient.authorize(this,callback);
    }

    private void initRecyclerView(){
        adapter = new AuthAdapter(R.layout.item_owner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (!realm.isEmpty()){
            adapter.updateData(realm.where(RealmAccountModel.class).findAll(),false);
        }
    }

    private void initRealmAccount(TwitterSession session){
        Disposable disposable = new TwitterApiProvider(session)
                .getUser(session.getUserId())
                .subscribeOn(Schedulers.io())
                .map(user -> new RealmAccountModel(
                        user,
                        session.getAuthToken(),
                        Picasso.get().load(user.profileImageUrlHttps).get()
                ))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(realmUnit -> realm.executeTransaction(realm -> realm.insertOrUpdate(realmUnit)))
                .subscribe(
                        result -> adapter.insertItem(result),
                        error -> Log.d(TAG, "onError: " + error.getMessage())
                );
    }

    private Callback<TwitterSession> callback = new Callback<TwitterSession>() {
        @Override
        public void success(Result<TwitterSession> result) {
            TwitterSession session = result.data;
            initRealmAccount(session);
            Log.d(TAG, "success: " + result.data.getUserName());
        }

        @Override
        public void failure(TwitterException exception) {
            Toast.makeText(AuthTweeterActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

}
