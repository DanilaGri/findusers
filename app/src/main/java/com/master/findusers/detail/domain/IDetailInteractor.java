package com.master.findusers.detail.domain;

import com.master.findusers.search.domain.model.User;

import io.reactivex.Flowable;

public interface IDetailInteractor {
    Flowable<User> findByName(String name);
}
