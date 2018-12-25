package com.master.findusers.recent.domain;

import com.master.findusers.search.domain.model.User;

import java.util.List;

import io.reactivex.Flowable;

public interface IRecentUsersInteractor {

    Flowable<List<User>> findAll();
}
