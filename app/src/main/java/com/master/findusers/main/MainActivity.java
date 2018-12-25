package com.master.findusers.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.master.findusers.R;
import com.master.findusers.base.BaseActivity;
import com.master.findusers.detail.presentation.DetailFragment;
import com.master.findusers.recent.presentation.RecentUsersFragment;
import com.master.findusers.search.presentation.SearchUserFragment;

public class MainActivity extends BaseActivity implements OnUserClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            openFragmentWithBackStack(SearchUserFragment.newInstance(), SearchUserFragment.TAG);
        }
    }

    @Override
    public void onUserClick(String user) {
        openFragmentWithBackStack(DetailFragment.newInstance(user), DetailFragment.TAG);
    }

    @Override
    public void openRecent() {
        openFragmentWithBackStack(RecentUsersFragment.newInstance(), RecentUsersFragment.TAG);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int backCount = fragmentManager.getBackStackEntryCount();
        if (backCount > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }

}
