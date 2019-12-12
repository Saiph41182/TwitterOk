package com.example.twitterok.databinding.viewmodel;

import android.os.Binder;

import androidx.annotation.VisibleForTesting;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitterok.R;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.repository.TweetsRepository;
import com.example.twitterok.view.adapters.AnimatedMainAdapter;
import com.example.twitterok.view.adapters.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class TimelineViewModel extends DataBinderViewModel<TweetModel> {

    private TweetsRepository tweetsRepo;

    public TimelineViewModel() {
        super();
        tweetsRepo = new TweetsRepository();
        adapter = new AnimatedMainAdapter(R.layout.item_tweet_view);
    }

    @Override
    public void setUp(){
        Disposable subscribe = tweetsRepo.getData()
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

    @BindingAdapter({"app:adapter","app:data"})
    public static void bind(RecyclerView recyclerView,BaseAdapter<TweetModel> adapter,List<TweetModel> data){
        recyclerView.setAdapter(adapter);
        adapter.updateData(data,false);
    }

}
