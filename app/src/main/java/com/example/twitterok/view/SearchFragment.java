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
import com.example.twitterok.R;
import com.example.twitterok.databinding.FragmentSearchBinding;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;
import com.example.twitterok.databinding.viewmodel.SearchUsersViewModel;
import com.example.twitterok.view.adapters.SearchAdapter;
import com.example.twitterok.repository.internet.TwitterApiProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private SearchUsersViewModel viewModel;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        if(viewModel != null){
            viewModel.setUp();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSearchBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false);
        binding.etHeaderItemSearch.setOnEditorActionListener(onEditorActionListener);
        View view = binding.getRoot();
        initRecyclerView(view);
        initViewBinding(binding);
        return view;
    }

    private void initViewBinding(FragmentSearchBinding binding) {
        if(viewModel == null){
            viewModel = new SearchUsersViewModel();
        }
        binding.setViewModel(viewModel);
        binding.setOwnerVM(new OwnerViewModel(App.getOwner()));
        binding.executePendingBindings();
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.users_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @Override
    public void onPause() {
        super.onPause();
        if(viewModel != null){
            viewModel.tearDown();
        }
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
