package com.example.twitterok.view.adapters;

import androidx.annotation.LayoutRes;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.databinding.viewmodel.TweetViewModel;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;

public class MainAdapter extends BaseAdapter<TweetModel>{

    @LayoutRes private int layoutId;

    public MainAdapter(@LayoutRes int layoutId){
        this.layoutId = layoutId;
        setHeader(R.layout.header_item_tweet_view);
    }

    @Override
    protected Object getObjectForPosition(int position) {
        return position == 0 ? new OwnerViewModel(App.getOwner()) : new TweetViewModel(data.get(position));
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return position == 0 ? header : layoutId;
    }

}

