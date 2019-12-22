package com.example.twitterok.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.databinding.ActivityProfileBinding;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;
import com.example.twitterok.databinding.viewmodel.ProfileTimelineViewModel;
import com.example.twitterok.databinding.viewmodel.UserViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.models.User;

import java.lang.reflect.Type;

public class ProfileActivity extends AppCompatActivity {


    private ProfileTimelineViewModel viewModel;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        initRecyclerView(binding.getRoot());
        Gson gson = new GsonBuilder().create();
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("user_json")){
            initViewModels(gson.fromJson(intent.getStringExtra("user_json"), User.class));
        }else{
            //initViewModels(App.getOwner());
        }
        binding.setViewModel(viewModel);
        binding.setUserViewModel(userViewModel);
        binding.profileFab.setOnClickListener(view ->{
            LinearLayout expendableView = binding.profileInnerAppBar;
            if(expendableView.getHeight() == 0){
                TransitionManager.beginDelayedTransition(binding.profileAppBar,new AutoTransition());
                expendableView.setLayoutParams(new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                binding.profileFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
            }else{
                TransitionManager.beginDelayedTransition(binding.profileAppBar,new AutoTransition());
                expendableView.setLayoutParams(new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
                binding.profileFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
            }

        });
    }

    private void initViewModels(User user) {
        userViewModel = new UserViewModel(user);
        viewModel = new ProfileTimelineViewModel(user.screenName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(viewModel != null){
            viewModel.setUp();
        }
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.user_info_tweet_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(viewModel!=null){
            viewModel.tearDown();
        }
    }
}
