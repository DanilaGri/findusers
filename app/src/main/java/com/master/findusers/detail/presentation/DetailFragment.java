package com.master.findusers.detail.presentation;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.master.findusers.R;
import com.master.findusers.base.BaseFragment;
import com.master.findusers.search.domain.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailFragment extends BaseFragment implements DetailView {
    public static final String TAG = DetailFragment.class.getSimpleName();

    @InjectPresenter
    DetailPresenter mDetailPresenter;

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.progress_bar)
    FrameLayout mProgressBar;
    @BindView(R.id.fd_name)
    TextView mName;
    @BindView(R.id.fd_height)
    TextView mHeight;
    @BindView(R.id.fd_mass)
    TextView mMass;
    @BindView(R.id.fd_hair_color)
    TextView mHairColor;
    @BindView(R.id.fd_skin_color)
    TextView mSkinColor;
    @BindView(R.id.fd_eye_color)
    TextView mEyeColor;
    @BindView(R.id.fd_birth_year)
    TextView mBirthYear;
    @BindView(R.id.fd_gender)
    TextView mGender;
    Unbinder unbinder;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String userName) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("userName", userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment when activity is re-initialized
        setRetainInstance(true);
        if (getArguments() != null) {
            mDetailPresenter.setUser(getArguments().getString("userName"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initToolBar(mToolBar, getString(R.string.personage_detail), true);
        mDetailPresenter.getUserByName();
        return view;
    }

    @Override
    public void showUser(User user) {
        mName.append(user.getName() == null ? ": -" : ": " + user.getName());
        mHeight.append(user.getHeight() == null ? ": -" : ": " + user.getHeight());
        mMass.append(user.getMass() == null ? ": -" : ": " + user.getMass());
        mHairColor.append(user.getHairColor() == null ? ": -" : ": " + user.getHairColor());
        mSkinColor.append(user.getSkinColor() == null ? ": -" : ": " + user.getSkinColor());
        mEyeColor.append(user.getEyeColor() == null ? ": -" : ": " + user.getEyeColor());
        mBirthYear.append(user.getBirthYear() == null ? ": -" : ": " + user.getBirthYear());
        mGender.append(user.getGender() == null ? ": -" : ": " + user.getGender());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
