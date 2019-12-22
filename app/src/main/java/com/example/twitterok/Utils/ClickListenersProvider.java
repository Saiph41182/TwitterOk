package com.example.twitterok.Utils;

import android.view.View;

import com.example.twitterok.view.BaseFragment;

public class ClickListenersProvider {

    private static BaseFragment.ViewModelClickListener clickListener;

    public static BaseFragment.ViewModelClickListener getClickListener(){
        return clickListener;
    }

    public static void setClickListener(BaseFragment.ViewModelClickListener onClickListener){
        clickListener = onClickListener;
    }
}
