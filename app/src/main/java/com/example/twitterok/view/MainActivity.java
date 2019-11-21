package com.example.twitterok.view;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;
import com.example.twitterok.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_fab_in_bab)
    public FloatingActionButton floatingActionButton;

    @BindView(R.id.main_bottom_app_bar)
    public BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomAppBar();
        if (savedInstanceState == null) {
            beginFragment(new MainFragment());
        }
    }

    private void initBottomAppBar() {
        bottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu_primary);
        bottomAppBar.setNavigationOnClickListener(onNavItemClickListener);
        bottomAppBar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @OnClick(R.id.main_fab_in_bab)
    public void clickOnFab(View view) {
        Intent intent = new Intent(this, NewTweetActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (!getSupportFragmentManager().findFragmentById(R.id.main_frame_layout).getTag().equals(MainFragment.class.getSimpleName())) {
            if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_END) {
                babChangeState(true);
            }
            beginFragment(new MainFragment());
        } else {
            super.onBackPressed();
        }
    }

    private void beginFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag = fragment.getClass().getSimpleName();
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout, fragment, tag).commit();
        fragmentManager.executePendingTransactions();
    }

    //true = primary, false = secondary

    private void babChangeState(boolean flag) {
        bottomAppBar.setFabAlignmentMode(flag ? BottomAppBar.FAB_ALIGNMENT_MODE_CENTER : BottomAppBar.FAB_ALIGNMENT_MODE_END);
        bottomAppBar.replaceMenu(flag ? R.menu.bottom_app_bar_menu_primary : R.menu.bottom_app_bar_menu_secondary);
    }

    private BottomAppBar.OnMenuItemClickListener onMenuItemClickListener = item -> {
        switch (item.getItemId()) {
            case R.id.bnb_home:
                babChangeState(true);
                beginFragment(new MainFragment());
                break;
            case R.id.bnb_search:
                babChangeState(false);
                beginFragment(new SearchFragment());
                break;
        }
        return true;
    };

    private Toolbar.OnClickListener onNavItemClickListener = item -> {
        BottomNavigationDrawerFragment drawerFragment = new BottomNavigationDrawerFragment();
        drawerFragment.show(getSupportFragmentManager(), drawerFragment.getTag());
    };
}
