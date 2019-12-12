package com.example.twitterok.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.databinding.ActivityProfileBinding;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;
import com.example.twitterok.databinding.viewmodel.ProfileTimelineViewModel;
import com.example.twitterok.databinding.viewmodel.TimelineViewModel;
import com.example.twitterok.view.adapters.MainAdapter;
import com.example.twitterok.repository.internet.TwitterApiProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileActivity extends AppCompatActivity {


    private ProfileTimelineViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        initRecyclerView(binding.getRoot());
        binding.setOwnerVM(new OwnerViewModel(App.getOwner()));
        viewModel = new ProfileTimelineViewModel();
        binding.setViewModel(viewModel);
        binding.profileBaseInfo.setOnClickListener(view -> {
            LinearLayout expendableView = binding.expendableView;
            if(expendableView.getVisibility() == View.GONE){
                TransitionManager.beginDelayedTransition(binding.profileAppBar,new AutoTransition());
                expendableView.setVisibility(View.VISIBLE);
                binding.profileArrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            }else{
                TransitionManager.beginDelayedTransition(binding.profileAppBar,new AutoTransition());
                expendableView.setVisibility(View.GONE);
                binding.profileArrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        });
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
