package com.master.findusers.search.presentation;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.master.findusers.R;
import com.master.findusers.base.BaseFragment;
import com.master.findusers.main.OnUserClickListener;
import com.master.findusers.search.domain.model.User;
import com.master.findusers.util.DialogUtil;
import com.master.findusers.util.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchUserFragment extends BaseFragment implements SearchUserView {
    public static final String TAG = SearchUserFragment.class.getSimpleName();

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.progress_bar)
    FrameLayout mProgressBar;
    @BindView(R.id.list)
    RecyclerView mList;
    Unbinder unbinder;
    @BindView(R.id.search)
    SearchView mSearchView;
    @BindView(R.id.recent)
    AppCompatButton mRecent;
    @InjectPresenter
    SearchUserPresenter mPresenter;
    @BindView(R.id.emptyView)
    FrameLayout mEmptyView;
    private OnUserClickListener mOnUserClickListener;
    private SearchUserAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchUserFragment() {
    }

    public static SearchUserFragment newInstance() {
        SearchUserFragment fragment = new SearchUserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupSearch();
        initToolBar(mToolBar, getString(R.string.search_user), false);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SearchUserAdapter(new ArrayList<>(), position -> {
            User user = mAdapter.getUserList().get(position);
            mPresenter.saveUser(user);
        });
        mList.setAdapter(mAdapter);
        return view;
    }

    private void setupSearch() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.searchUser(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setActivated(true);
        mSearchView.setQueryHint(getString(R.string.search_personage));
        mSearchView.onActionViewExpanded();
        mSearchView.setIconified(false);
        mSearchView.clearFocus();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            // Get the search close button image view
            ImageView closeButton = (ImageView) mSearchView.findViewById(R.id.search_close_btn);

            // Set on click listener
            closeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Find EditText view
                    EditText et = (EditText) mSearchView.findViewById(R.id.search_src_text);

                    //Clear the text from EditText view
                    et.setText("");

                    //Clear query
                    mSearchView.setQuery("", false);
                    mAdapter.getUserList().clear();
                    mAdapter.notifyDataSetChanged();
                    mEmptyView.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter.getUsersResponse() != null && mAdapter != null) {
            showUsers(mPresenter.getUsersResponse().getResults());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showUsers(List<User> usersResponse) {
        if (usersResponse == null || usersResponse.isEmpty()) {
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
            mAdapter.setUserList(usersResponse);
        }
    }

    @Override
    public void saveSuccess(String name) {
        mOnUserClickListener.onUserClick(name);
        KeyboardUtil.hideKeyboard(getView());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {
        DialogUtil.getInstance().showErrorDialog(getActivity(), e);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserClickListener) {
            try {
                mOnUserClickListener = (OnUserClickListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + " must implement OnUserClickListener");
            }
        }
    }

    @OnClick(R.id.recent)
    public void onViewClicked() {
        mOnUserClickListener.openRecent();
        KeyboardUtil.hideKeyboard(getView());
    }
}
