package com.example.twitterok.databinding.viewmodel;

import android.util.Log;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitterok.R;
import com.example.twitterok.repository.SearchRepository;
import com.example.twitterok.view.adapters.BaseAdapter;
import com.example.twitterok.view.adapters.SearchAdapter;
import com.twitter.sdk.android.core.models.User;

import java.util.List;
import java.util.function.UnaryOperator;

import io.reactivex.disposables.Disposable;

public class SearchUsersViewModel extends DataBinderViewModel<User> {

    private SearchRepository searchRepo;
    private String text;

    public SearchUsersViewModel() {
        super();
        searchRepo = new SearchRepository();
        adapter = new SearchAdapter(R.layout.item_user);
    }
    @Override
    public void setUp() {
        Log.d("SearchUsersViewModel", "setUp: text: " + text);
        populateContent(text == null ? "a" : text);
    }

    public void populateContent(String contentBy){
        Disposable subscribe = searchRepo.getData(contentBy)
                .doOnSubscribe(result -> setLoading(true))
                .doOnTerminate(() -> setLoading(false))
                .subscribe(res -> {
                    data.clear();
                    data.addAll(res);
                    notifyPropertyChanged(BR.data);
                });
    }

    @Override
    public void tearDown() {

    }

    @Bindable
    public String getText(){
        return text;
    }

    public void setText(String text){
        if(this.text != null && text != null) {
            if (!this.text.equals(text)) {
                this.text = text;

                notifyPropertyChanged(BR.text);
            }
        }else{
            this.text = text;
        }
    }


    @BindingAdapter({"app:adapter","app:data"})
    public static void bind(RecyclerView recyclerView, BaseAdapter<User> adapter, List<User> data){
        recyclerView.setAdapter(adapter);
        adapter.updateData(data,true);
    }
}
