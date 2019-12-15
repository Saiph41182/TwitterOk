package com.example.twitterok.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.twitterok.App;
import com.example.twitterok.BR;
import com.example.twitterok.R;
import com.example.twitterok.databinding.FragmentSearchBinding;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;
import com.example.twitterok.databinding.viewmodel.SearchUsersViewModel;
import com.example.twitterok.databinding.viewmodel.TimelineViewModel;
import com.example.twitterok.view.adapters.SearchAdapter;
import com.example.twitterok.repository.internet.TwitterApiProvider;
import com.twitter.sdk.android.core.models.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment<User> {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSearchBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false);
        binding.etHeaderItemSearch.setOnEditorActionListener(onEditorActionListener);
        initRecyclerView(binding);
        initViewModel(new SearchUsersViewModel());
        initVariable(binding, com.example.twitterok.BR.viewModel,viewModel);
        initVariable(binding,BR.ownerVM,new OwnerViewModel(App.getOwner()));
        return binding.getRoot();
    }

    private void initRecyclerView(FragmentSearchBinding binding){
        RecyclerView recyclerView = binding.usersList;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    private TextView.OnEditorActionListener onEditorActionListener = (v, actionId, event) -> {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            viewModel.setUp();
            InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
            return true;
        }
        return false;
    };

}
