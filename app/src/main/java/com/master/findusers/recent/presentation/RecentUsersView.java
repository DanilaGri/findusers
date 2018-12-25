package com.master.findusers.recent.presentation;

import com.master.findusers.base.BaseView;
import com.master.findusers.search.domain.model.User;

import java.util.List;

public interface RecentUsersView extends BaseView {
    void showUsers(List<User> list);
}
