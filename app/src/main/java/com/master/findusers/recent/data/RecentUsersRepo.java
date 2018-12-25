package com.master.findusers.recent.data;

import com.master.findusers.search.domain.model.User;
import com.master.findusers.search.domain.model.UserDao;

import java.util.List;

import io.reactivex.Flowable;

public class RecentUsersRepo implements IRecentUsersRepo {

    private UserDao mUserDao;

    public RecentUsersRepo(UserDao mUserDao) {
        this.mUserDao = mUserDao;
    }

    @Override
    public Flowable<List<User>> findAll() {
        return mUserDao.findAll();
    }
}
