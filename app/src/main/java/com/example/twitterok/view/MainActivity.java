package com.example.twitterok.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.twitterok.App;
import com.example.twitterok.databinding.NavDrawerHeaderBinding;
import com.example.twitterok.databinding.viewmodel.OwnerViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.example.twitterok.R;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_fab_in_bab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView bottomNavView;

    @BindView(R.id.main_navigation_drawer)
    NavigationView navigationView;

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mainDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNavView();
        bottomNavView.setOnNavigationItemSelectedListener(selectedListener);
        if (savedInstanceState == null) {
            beginFragment(new HomeFragment());
        }
    }

    @OnClick(R.id.main_fab_in_bab)
    public void clickOnFab(View view) {
        startActivity(NewTweetActivity.class);
    }

    @Override
    public void onBackPressed() {
        if (!getSupportFragmentManager().findFragmentById(R.id.main_frame_layout).getTag().equals(HomeFragment.class.getSimpleName())) {
            bottomNavView.setSelectedItemId(R.id.bn_home);
            beginFragment(new HomeFragment());
        } else {
            super.onBackPressed();
        }
    }

    public void showDrawer(){
        mainDrawer.openDrawer(GravityCompat.START);
    }

    public void fabVisibilityHandle(boolean doVisible){
        if (doVisible) {
            floatingActionButton.show();
        } else {
            floatingActionButton.hide();
        }
    }

    private void initNavView(){
        NavDrawerHeaderBinding binding = DataBindingUtil.inflate(getLayoutInflater()
                ,R.layout.nav_drawer_header
                ,navigationView,false);
        navigationView.addHeaderView(binding.getRoot());
        navigationView.setNavigationItemSelectedListener(navigationViewItemSelectedListener);
        binding.setUvm(new OwnerViewModel(App.getOwner()));
    }

    private void beginFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag = fragment.getClass().getSimpleName();
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout, fragment, tag).commit();
        fragmentManager.executePendingTransactions();
    }

    private void startActivity(Class clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    private void testToast(){
        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
    }

    private OnNavigationItemSelectedListener selectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.bn_home:
                beginFragment(new HomeFragment());
                break;
            case R.id.bn_search:
                beginFragment(new SearchFragment());
                break;
            case R.id.bn_notification:
                break;
            case R.id.bn_messages:
                break;
        }
        return true;
    };

    private NavigationView.OnNavigationItemSelectedListener navigationViewItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.nv_profile:
                startActivity(ProfileActivity.class);
                break;
            case R.id.nv_lists:
            case R.id.nv_settings:
                testToast();
                break;
        }
        return true;
    };

}
