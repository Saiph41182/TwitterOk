package com.example.twitterok.view.adapters;

import androidx.annotation.LayoutRes;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;
import com.example.twitterok.databinding.viewmodel.UserViewModel;
import com.twitter.sdk.android.core.models.User;

public class SearchAdapter extends BaseAdapter<User>  {

    @LayoutRes private int layoutRes;

    public SearchAdapter(@LayoutRes int layoutRes) {
        this.layoutRes = layoutRes;
        setHeader(R.layout.header_item_tweet_view);
    }

    @Override
    protected Object getObjectForPosition(int position) {
        return position == 0 ? new OwnerViewModel(App.getOwner()) : new UserViewModel(data.get(position));
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return position == 0 ? header : layoutRes;
    }
}
