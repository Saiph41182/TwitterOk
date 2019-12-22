package com.example.twitterok.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitterok.R;
import com.example.twitterok.Utils.ClickListenersProvider;
import com.example.twitterok.databinding.viewmodel.DataBinderViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseFragment<T> extends Fragment {

    public interface ViewModelClickListener{

        void onClick(View view, String Text);

    }

    protected DataBinderViewModel<T> viewModel;

    public BaseFragment() {

    }


    void initViewModel(@NonNull DataBinderViewModel<T> viewModel){
        if(this.viewModel == null) {
            this.viewModel = viewModel;
        }
    }

    <V extends BaseObservable> void initVariable(ViewDataBinding binding, int BRid, V viewModel){
        binding.setVariable(BRid,viewModel);
        binding.executePendingBindings();
    }

    void handleFabVisibility(RecyclerView recyclerView){
        MainActivity activity = (MainActivity) getActivity();
        assert(activity != null);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0){
                        activity.fabVisibilityHandle(false);
                    }else {
                        activity.fabVisibilityHandle(true);
                    }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ClickListenersProvider.setClickListener(clickListener);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (viewModel != null){
            viewModel.setUp();
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        if(viewModel != null){
            viewModel.tearDown();
        }
    }

    private ViewModelClickListener clickListener = (view,text) -> {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_tweet_icon:
                intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("user_json",text);
                startActivity(intent);
                break;
            case R.id.tv_tweet_url_field: ;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(text)));
                break;
            case R.id.cv_tweet_card:
                Toast.makeText(view.getContext(), "tweet full activity", Toast.LENGTH_SHORT).show();
                /*intent = new Intent(getActivity(), TweetActivity.class);
                intent.putExtra("tweetID",text);
                startActivity(intent);*/
                break;
            case R.id.iv_main_tweet_picture:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, new ContentShowerFragment()).commit();
                break;

        }
    };
}
