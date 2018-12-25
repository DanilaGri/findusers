package com.master.findusers.detail.presentation;

import com.arellomobile.mvp.MvpView;
import com.master.findusers.search.domain.model.User;

public interface DetailView extends MvpView {
    void showUser(User user);
}
