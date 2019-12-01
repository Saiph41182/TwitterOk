package com.example.twitterok.view.adapters;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import android.view.animation.AnimationUtils;

import com.example.twitterok.R;

public class AnimatedMainAdapter extends MainAdapter{

    public AnimatedMainAdapter(@LayoutRes int resId) {
        super(resId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.CommonViewHolder holder, int i) {
        super.onBindViewHolder(holder, i);
        holder.
                itemView.
                setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.news_item_anim));
    }
}
