package com.example.twitterok.databinding.viewmodel;

import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.repository.ProfileTweetsRepository;
import com.example.twitterok.view.adapters.BaseAdapter;
import com.example.twitterok.view.adapters.MainAdapter;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ProfileTimelineViewModel extends DataBinderViewModel<TweetModel> {

    private ProfileTweetsRepository repository;

    public ProfileTimelineViewModel() {
        super();
        repository = new ProfileTweetsRepository();
        adapter = new MainAdapter(R.layout.item_tweet_view);
    }

    @Override
    public void setUp() {
        Disposable disposable = repository.getData(App.getOwner().getScreenName())
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
