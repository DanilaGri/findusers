package com.master.findusers.recent.domain;

import com.master.findusers.recent.data.IRecentUsersRepo;
import com.master.findusers.search.domain.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RecentUsersInteractor implements IRecentUsersInteractor {

    private IRecentUsersRepo mRepo;

    @Inject
    public RecentUsersInteractor(IRecentUsersRepo mRepo) {
        this.mRepo = mRepo;
    }

    @Override
    public Flowable<List<User>> findAll() {
        return mRepo.findAll();
    }
}
