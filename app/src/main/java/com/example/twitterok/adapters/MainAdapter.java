package com.example.twitterok.adapters;

import androidx.annotation.LayoutRes;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.viewmodel.MainDataViewModel;
import com.example.twitterok.viewmodel.OwnerViewModel;
import com.example.twitterok.viewmodel.UserViewModel;

public class MainAdapter extends BaseAdapter<TweetModel>{

    @LayoutRes private int layoutId;

    public MainAdapter(@LayoutRes int layoutId){
        this.layoutId = layoutId;
        setHeader(R.layout.header_item_tweet_view);
    }

    @Override
    protected Object getObjectForPosition(int position) {
        return position == 0 ? new OwnerViewModel(App.getOwner()) : new MainDataViewModel(data.get(position));
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return position == 0 ? header : layoutId;
    }

}

