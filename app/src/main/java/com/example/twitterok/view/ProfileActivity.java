package com.example.twitterok.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.view.adapters.MainAdapter;
import com.example.twitterok.repository.internet.TwitterApiProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.user_info_tweet_list);
        MainAdapter adapter = new MainAdapter(R.layout.item_tweet_view);
        adapter.setHeader(R.layout.header_item_profile);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        downloadData(adapter);
    }

    private void downloadData(MainAdapter adapter) {
        TwitterApiProvider twitterApiClient = new TwitterApiProvider(App.getSession());
        Disposable disposable = twitterApiClient
                .getUserTimeline(200,App.getOwner().getScreenName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> adapter.updateData(data));
    }


}
