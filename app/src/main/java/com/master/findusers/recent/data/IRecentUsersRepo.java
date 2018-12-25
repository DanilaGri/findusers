package com.master.findusers.recent.data;

import com.master.findusers.search.domain.model.User;

import java.util.List;

import io.reactivex.Flowable;

public interface IRecentUsersRepo {

    Flowable<List<User>> findAll();
}
