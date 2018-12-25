package com.master.findusers.base;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.master.findusers.R;

public abstract class BaseFragment extends MvpAppCompatFragment {

    public void initToolBar(Toolbar toolbar, String title, boolean isShowBack) {
        toolbar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        toolbar.setTitle(title);
        if (isShowBack) {
            toolbar.setNavigationIcon(R.drawable.ic_back_button);
            toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        }
    }
}
