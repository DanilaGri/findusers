package com.master.findusers.search.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.master.findusers.base.App;
import com.master.findusers.base.BasePresenter;
import com.master.findusers.di.scopes.SearchScope;
import com.master.findusers.search.di.SearchModule;
import com.master.findusers.search.domain.interactor.SearchInteractor;
import com.master.findusers.search.domain.model.User;
import com.master.findusers.search.domain.model.UsersResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@SearchScope
@InjectViewState
public class SearchUserPresenter extends BasePresenter<SearchUserView> {
    @Inject
    SearchInteractor mInteractor;
    private UsersResponse mUsersResponse;

    public SearchUserPresenter() {
        App.getApp().plusSearchModule(new SearchModule()).inject(this);
    }

    public UsersResponse getUsersResponse() {
        return mUsersResponse;
    }

    public void setUsersResponse(UsersResponse mUsersResponse) {
        this.mUsersResponse = mUsersResponse;
    }

    public void searchUser(String text) {
        getViewState().showProgress();
        Disposable disposable = mInteractor
                .findUsers(text)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<UsersResponse>() {
                    @Override
                    public void onNext(UsersResponse response) {
                        mUsersResponse = response;
                        getViewState().hideProgress();
                        getViewState().showUsers(mUsersResponse.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().hideProgress();
                        getViewState().showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        setDisposable(disposable);
    }

    public void saveUser(User user) {
        Disposable disposable = mInteractor
                .insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long response) {
                        getViewState().hideProgress();
                        getViewState().saveSuccess(user.getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().hideProgress();
                        getViewState().showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        setDisposable(disposable);
    }
}

