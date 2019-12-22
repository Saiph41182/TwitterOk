package com.example.twitterok.view.adapters;

import android.util.Log;

import androidx.annotation.LayoutRes;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.Utils.ClickListenersProvider;
import com.example.twitterok.databinding.viewmodel.TweetViewModel;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;

public class MainAdapter extends BaseAdapter<TweetModel>{

    @LayoutRes private int layoutId;

    public MainAdapter(@LayoutRes int layoutId){
        this.layoutId = layoutId;
    }

    @Override
    protected Object getObjectForPosition(int position) {
        return new TweetViewModel(data.get(position), ClickListenersProvider.getClickListener());
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return layoutId;
    }

}

