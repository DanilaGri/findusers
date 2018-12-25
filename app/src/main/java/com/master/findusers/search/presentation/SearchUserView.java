package com.master.findusers.search.presentation;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.master.findusers.base.BaseView;
import com.master.findusers.search.domain.model.User;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface SearchUserView extends BaseView {
    void showUsers(List<User> list);

    void saveSuccess(String name);
}
