package com.example.twitterok.view.adapters;

import androidx.annotation.LayoutRes;

import com.example.twitterok.R;
import com.example.twitterok.repository.realm.RealmAccountModel;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;

public class AuthAdapter extends BaseAdapter<RealmAccountModel> {

    @LayoutRes private int layoutId;

    public AuthAdapter(@LayoutRes int layoutId){
        this.layoutId = layoutId;
        setHeader(R.layout.header_item_auth_users);
    }

    public void insertItem(RealmAccountModel owner){
        data.add(owner);
        this.notifyItemInserted(data.size() - 1);
        this.notifyDataSetChanged();
    }

    @Override
    protected Object getObjectForPosition(int position) {
        return (data.isEmpty() || position == 0) ? null : new OwnerViewModel(data.get(position));
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return position == 0 ? header : layoutId;
    }
}
