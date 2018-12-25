package com.master.findusers.detail.di;

import com.master.findusers.detail.data.DetailRepo;
import com.master.findusers.detail.data.IDetailRepo;
import com.master.findusers.detail.domain.DetailInteractor;
import com.master.findusers.detail.domain.IDetailInteractor;
import com.master.findusers.di.scopes.DetailScope;
import com.master.findusers.search.domain.model.UserDao;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {

    @Provides
    @DetailScope
    public IDetailRepo provideIDetailRepo(UserDao dao) {
        return new DetailRepo(dao);
    }

    @Provides
    @DetailScope
    public IDetailInteractor provideIDetailInteractor(IDetailRepo repo) {
        return new DetailInteractor(repo);
    }
}
