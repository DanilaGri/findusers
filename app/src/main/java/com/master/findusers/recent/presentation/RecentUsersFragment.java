package com.master.findusers.recent.presentation;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.master.findusers.R;
import com.master.findusers.base.BaseFragment;
import com.master.findusers.main.OnUserClickListener;
import com.master.findusers.search.domain.model.User;
import com.master.findusers.search.presentation.SearchUserAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentUsersFragment extends BaseFragment implements RecentUsersView {
    public static final String TAG = RecentUsersFragment.class.getSimpleName();


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.progress_bar)
    FrameLayout progressBar;
    @BindView(R.id.recentList)
    RecyclerView recentList;
    Unbinder unbinder;

    @InjectPresenter
    RecentUsersPresenter mPresenter;
    private SearchUserAdapter mAdapter;
    private OnUserClickListener mOnUserClickListener;


    public RecentUsersFragment() {
        // Required empty public constructor
    }

    public static RecentUsersFragment newInstance() {
        RecentUsersFragment fragment = new RecentUsersFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent_users, container, false);
        unbinder = ButterKnife.bind(this, view);
        initToolBar(mToolBar, getString(R.string.viewed_characters), true);
        recentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SearchUserAdapter(getActivity(), new ArrayList<>(), position -> {
            User user = mAdapter.getUserList().get(position);
            mOnUserClickListener.onUserClick(user.getName());
        });
        recentList.setAdapter(mAdapter);
        mPresenter.recentAllUsers();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _onAttach(context);
    }

    private void _onAttach(Context context) {

        Activity activity;

        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                mOnUserClickListener = (OnUserClickListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement OnUserClickListener");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showUsers(List<User> list) {
        mAdapter.setUserList(list);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {

    }
}
