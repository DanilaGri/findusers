package com.master.findusers.search.data;

import com.master.findusers.network.API;
import com.master.findusers.search.domain.model.User;
import com.master.findusers.search.domain.model.UserDao;
import com.master.findusers.search.domain.model.UsersResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class SearchRepo implements ISearchRepo {
    private API mApi;
    private UserDao mUserDao;

    public SearchRepo(API mApi, UserDao userDao) {
        this.mApi = mApi;
        this.mUserDao = userDao;
    }

    @Override
    public Observable<UsersResponse> findUsers(String search) {
        return mApi.findUsers(search);
    }

    @Override
    public Observable<Long> insertUser(User user) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mUserDao.insert(user);
            }
        });
    }
}
