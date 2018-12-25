package com.master.findusers.detail.data;

import com.master.findusers.search.domain.model.User;

import io.reactivex.Flowable;

public interface IDetailRepo {
    Flowable<User> findByName(String name);
}
