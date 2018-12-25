package com.master.findusers.detail.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.master.findusers.base.App;
import com.master.findusers.base.BasePresenter;
import com.master.findusers.detail.di.DetailModule;
import com.master.findusers.detail.domain.DetailInteractor;
import com.master.findusers.di.scopes.DetailScope;
import com.master.findusers.search.domain.model.User;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@DetailScope
@InjectViewState
public class DetailPresenter extends BasePresenter<DetailView> {
    @Inject
    DetailInteractor mInteractor;
    private String mUserName;


    public DetailPresenter() {
        App.getApp().plusDetailModule(new DetailModule()).inject(this);
    }

    public void setUser(String userName) {
        this.mUserName = userName;
    }

    public void getUserByName() {
        Disposable disposable = mInteractor
                .findByName(mUserName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        getViewState().showUser(user);
                    }
                });
        setDisposable(disposable);
    }

}
