package com.example.twitterok.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.adapters.SearchAdapter;
import com.example.twitterok.internet.MyTwitterApiClient;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends BaseObservable {

    private List<User> users;
    private SearchAdapter searchAdapter;
    private Disposable disposable;

    public SearchViewModel() {
        users = new ArrayList<>();
        searchAdapter = new SearchAdapter(R.layout.item_user);
    }

    public void setUp(){
        populateData();
    }

    public void tearDown(){
        destroyData();
    }

    private void destroyData() {
        disposable.dispose();
        users.clear();
    }
    private void populateData() {
        MyTwitterApiClient twitterApiClient = new MyTwitterApiClient(App.getSession());
        disposable = twitterApiClient
                .getSearch("a")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result -> users.addAll(result))
                .subscribe(result -> notifyPropertyChanged(BR.data));
    }

    @Bindable
    public List<User> getUsers(){
        return users;
    }

    @Bindable
    public SearchAdapter getItemAdapter(){
        return searchAdapter;
    }
}
