package com.example.twitterok.view;


import androidx.databinding.DataBindingUtil;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twitterok.App;
import com.example.twitterok.BR;
import com.example.twitterok.R;

import com.example.twitterok.databinding.FragmentHomeBinding;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;
import com.example.twitterok.databinding.viewmodel.TimelineViewModel;
import com.example.twitterok.model.TweetModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<TweetModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        initRecyclerView(binding);
        initViewModel(new TimelineViewModel());
        initVariable(binding,BR.viewModel,viewModel);
        initVariable(binding,BR.ownerVM,new OwnerViewModel(App.getOwner()));
        return binding.getRoot();
    }


    private void initRecyclerView(FragmentHomeBinding binding){
        RecyclerView recyclerView = binding.timelineRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

}
