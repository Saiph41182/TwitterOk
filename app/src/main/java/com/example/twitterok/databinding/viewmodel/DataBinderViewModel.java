package com.example.twitterok.databinding.viewmodel;

import androidx.annotation.VisibleForTesting;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitterok.model.TweetModel;
import com.example.twitterok.view.adapters.BaseAdapter;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;

public abstract class DataBinderViewModel<T> extends BaseObservable {

    protected BaseAdapter<T> adapter;
    protected List<T> data;
    protected boolean loading = false;

    DataBinderViewModel() {
        data = new ArrayList<>();
    }

    public abstract void setUp();

    public abstract void tearDown();

    @Bindable
    public List<T> getData(){
        return data;
    }

    @Bindable
    public BaseAdapter<T> getAdapter(){
        return adapter;
    }

    @Bindable
    public boolean isLoading(){
        return loading;
    }

    protected void setLoading(boolean isLoading){
        loading = isLoading;
        notifyPropertyChanged(BR.loading);
    }
}
