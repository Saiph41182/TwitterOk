package com.example.twitterok.view;


import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twitterok.App;
import com.example.twitterok.R;
import com.example.twitterok.adapters.AnimatedMainAdapter;
import com.example.twitterok.adapters.MainAdapter;
import com.example.twitterok.databinding.FragmentMainBinding;

import com.example.twitterok.viewmodel.DataViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    public static final String TAG = "MainFragment";

    private DataViewModel dataViewModel = new DataViewModel();

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        View view = binding.getRoot();
        initRecyclerView(view);
        binding.setViewModel(dataViewModel);
        return view;
    }
    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
            dataViewModel.setUp();
    }

    @Override
    public void onPause() {
        super.onPause();
        dataViewModel.tearDown();
    }


}
