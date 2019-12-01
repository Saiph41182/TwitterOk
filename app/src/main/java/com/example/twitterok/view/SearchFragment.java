package com.example.twitterok.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.view.adapters.SearchAdapter;
import com.example.twitterok.repository.internet.TwitterApiProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.users_list);
        SearchAdapter searchAdapter = new SearchAdapter(R.layout.item_user);
        searchAdapter.setHeader(R.layout.header_item_user_search);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        downloadUsers(searchAdapter);
    }

    private void downloadUsers(SearchAdapter searchAdapter){
        TwitterApiProvider twitterApiClient = new TwitterApiProvider(App.getSession());
        Disposable disposable = twitterApiClient
                .getSearch("a")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchAdapter::updateData);
    }
}
