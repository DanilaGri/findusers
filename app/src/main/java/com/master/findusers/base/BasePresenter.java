package com.master.findusers.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BasePresenter<T extends MvpView> extends MvpPresenter<T> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void setDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void destroyView(T view) {
        super.detachView(view);
        clearCompositeDisposable();
    }

    protected void clearCompositeDisposable() {
        if (compositeDisposable.size() > 0) {
            compositeDisposable.clear();
        }
    }
}
