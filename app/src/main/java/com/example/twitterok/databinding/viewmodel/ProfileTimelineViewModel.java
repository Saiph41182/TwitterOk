package com.example.twitterok.databinding.viewmodel;


import androidx.databinding.library.baseAdapters.BR;

import com.example.twitterok.R;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.repository.ProfileTweetsRepository;
import com.example.twitterok.view.adapters.MainAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ProfileTimelineViewModel extends DataBinderViewModel<TweetModel> {

    private ProfileTweetsRepository repository;
    private Observable<List<TweetModel>> observable;

    public ProfileTimelineViewModel(String screenName) {
        super();
        repository = new ProfileTweetsRepository();
        adapter = new MainAdapter(R.layout.item_tweet_view);
        initRepoService(screenName);
    }

    private void initRepoService(String screenName){
        observable = repository.getData(screenName);
    }

    @Override
    public void setUp() {
        Disposable disposable = observable
                .doOnSubscribe(result -> setLoading(true))
                .doOnTerminate(() -> setLoading(false))
                .subscribe(res -> {
                    data.addAll(res);
                    notifyPropertyChanged(BR.data);
                });
    }

    @Override
    public void tearDown() {

    }

}
