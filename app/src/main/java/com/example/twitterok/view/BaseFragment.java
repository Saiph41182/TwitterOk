package com.example.twitterok.view;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.twitterok.databinding.viewmodel.DataBinderViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseFragment<T> extends Fragment {

    protected DataBinderViewModel<T> viewModel;

    public BaseFragment() {

    }

    protected void initViewModel(@NonNull DataBinderViewModel<T> viewModel){
        if(this.viewModel == null) {
            this.viewModel = viewModel;
        }
    }

    protected <V extends BaseObservable> void initVariable(ViewDataBinding binding, int BRid, V viewModel){
        binding.setVariable(BRid,viewModel);
        binding.executePendingBindings();
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
}
