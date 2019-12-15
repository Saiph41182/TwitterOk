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
import com.example.twitterok.R;

import com.example.twitterok.databinding.FragmentMainBinding;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;
import com.example.twitterok.databinding.viewmodel.TimelineViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private TimelineViewModel viewModel;

    public MainFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        if (viewModel != null){
            viewModel.setUp();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        View view = binding.getRoot();
        initRecyclerView(view);
        viewModel = new TimelineViewModel();
        binding.setViewModel(viewModel);
        binding.setOwnerVM(new OwnerViewModel(App.getOwner()));
        return view;
    }
    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.timeline_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @Override
    public void onPause() {
        super.onPause();
        if(viewModel != null){
            viewModel.tearDown();
        }
    }
}
