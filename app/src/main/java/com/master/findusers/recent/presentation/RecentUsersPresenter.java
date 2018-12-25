package com.master.findusers.recent.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.master.findusers.base.App;
import com.master.findusers.base.BasePresenter;
import com.master.findusers.di.scopes.SearchScope;
import com.master.findusers.recent.di.RecentUsersModule;
import com.master.findusers.recent.domain.RecentUsersInteractor;
import com.master.findusers.search.domain.model.User;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@SearchScope
@InjectViewState
public class RecentUsersPresenter extends BasePresenter<RecentUsersView> {

    @Inject
    RecentUsersInteractor mInteractor;

    public RecentUsersPresenter() {
        App.getApp().plusRecentUsersModule(new RecentUsersModule()).inject(this);
    }


    public void recentAllUsers() {
        getViewState().showProgress();
        Disposable disposable = mInteractor
                .findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        getViewState().hideProgress();
                        Collections.reverse(users);
                        getViewState().showUsers(users);
                    }
                });
        setDisposable(disposable);
    }

}
