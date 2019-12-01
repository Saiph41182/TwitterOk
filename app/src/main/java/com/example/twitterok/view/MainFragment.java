package com.example.twitterok.view;


import androidx.databinding.DataBindingUtil;
import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twitterok.App;
import com.example.twitterok.R;

import com.example.twitterok.repository.internet.TwitterApiProvider;
import com.example.twitterok.view.adapters.AnimatedMainAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    public static final String TAG = "MainFragment";

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initRecyclerView(view);
        return view;
    }
    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.timeline_recycler_view);
        AnimatedMainAdapter adapter = new AnimatedMainAdapter(R.layout.item_tweet_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        downloadData(adapter);
    }

    public void downloadData(AnimatedMainAdapter mainAdapter){
        TwitterApiProvider twitterApiClient = new TwitterApiProvider(App.getSession());
        Disposable disposable = twitterApiClient
                .getHomeTimeline(200)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainAdapter::updateData);

    }

}
