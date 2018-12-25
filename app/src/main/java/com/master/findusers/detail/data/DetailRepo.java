package com.master.findusers.detail.data;

import com.master.findusers.search.domain.model.User;
import com.master.findusers.search.domain.model.UserDao;

import io.reactivex.Flowable;

public class DetailRepo implements IDetailRepo {

    private UserDao mUserDao;

    public DetailRepo(UserDao mUserDao) {
        this.mUserDao = mUserDao;
    }

    @Override
    public Flowable<User> findByName(String name) {
        return mUserDao.findByName(name);
    }
}
