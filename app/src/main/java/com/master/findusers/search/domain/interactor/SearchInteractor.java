package com.master.findusers.search.domain.interactor;

import com.master.findusers.search.data.ISearchRepo;
import com.master.findusers.search.domain.model.User;
import com.master.findusers.search.domain.model.UsersResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchInteractor implements ISearchInteractor {
    private ISearchRepo mRepo;

    @Inject
    public SearchInteractor(ISearchRepo mRepo) {
        this.mRepo = mRepo;
    }

    @Override
    public Observable<UsersResponse> findUsers(String search) {
        return mRepo.findUsers(search);
    }

    @Override
    public Observable<Long> insertUser(User user) {
        return mRepo.insertUser(user);
    }
}
