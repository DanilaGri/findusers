package com.master.findusers.search.domain.interactor;

import com.master.findusers.search.domain.model.User;
import com.master.findusers.search.domain.model.UsersResponse;

import io.reactivex.Observable;

public interface ISearchInteractor {
    Observable<UsersResponse> findUsers(String search);

    Observable<Long> insertUser(User user);
}
