package com.master.findusers.detail.domain;

import com.master.findusers.detail.data.IDetailRepo;
import com.master.findusers.search.domain.model.User;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class DetailInteractor implements IDetailInteractor {
    private IDetailRepo mDetailRepo;

    @Inject
    public DetailInteractor(IDetailRepo mDetailRepo) {
        this.mDetailRepo = mDetailRepo;
    }

    @Override
    public Flowable<User> findByName(String name) {
        return mDetailRepo.findByName(name);
    }
}
