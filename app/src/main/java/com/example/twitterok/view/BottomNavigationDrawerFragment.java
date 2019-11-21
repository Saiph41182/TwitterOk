package com.example.twitterok.view;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.twitterok.App;
import com.example.twitterok.internet.MyTwitterApiClient;
import com.example.twitterok.viewmodel.OwnerViewModel;
import com.example.twitterok.viewmodel.UserViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.twitterok.R;
import com.example.twitterok.databinding.FragmentBottomNavMenuBinding;
import com.example.twitterok.model.TweetModel;
import com.example.twitterok.viewmodel.MainDataViewModel;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import io.realm.Realm;

public class BottomNavigationDrawerFragment extends BottomSheetDialogFragment {

    private NavigationView navigationView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_nav_menu,container,false);
        FragmentBottomNavMenuBinding menuBinding = DataBindingUtil.bind(view);

        menuBinding.setUvm(new OwnerViewModel(App.getOwner()));
        navigationView = view.findViewById(R.id.bottom_nav_drawer_menu);
        navigationView.setNavigationItemSelectedListener(nvItemSelected);
        return view;
    }

    private NavigationView.OnNavigationItemSelectedListener nvItemSelected = menuItem -> {
        switch (menuItem.getItemId()){
            case R.id.btm_nav_profile:
                Intent intent = new Intent(this.getContext(),ProfileActivity.class);
                startActivity(intent);
                this.dismiss();
                break;
            case 1:
                break;
        }
        return false;
    };
}
