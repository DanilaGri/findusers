package com.master.findusers.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface BaseView extends MvpView {
    void showProgress();

    void hideProgress();

    void showError(Throwable e);
}
