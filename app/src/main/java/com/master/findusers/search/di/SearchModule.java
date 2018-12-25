package com.master.findusers.search.di;

import com.master.findusers.di.scopes.SearchScope;
import com.master.findusers.network.API;
import com.master.findusers.search.data.ISearchRepo;
import com.master.findusers.search.data.SearchRepo;
import com.master.findusers.search.domain.interactor.ISearchInteractor;
import com.master.findusers.search.domain.interactor.SearchInteractor;
import com.master.findusers.search.domain.model.UserDao;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {
    @Provides
    @SearchScope
    public ISearchRepo provideISearchRepo(API api, UserDao dao) {
        return new SearchRepo(api, dao);
    }

    @Provides
    @SearchScope
    public ISearchInteractor provideISearchInteractor(ISearchRepo repo) {
        return new SearchInteractor(repo);
    }
}
