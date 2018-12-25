package com.master.findusers.recent.di;

import com.master.findusers.di.scopes.RecentUsersScope;
import com.master.findusers.recent.data.IRecentUsersRepo;
import com.master.findusers.recent.data.RecentUsersRepo;
import com.master.findusers.recent.domain.IRecentUsersInteractor;
import com.master.findusers.recent.domain.RecentUsersInteractor;
import com.master.findusers.search.domain.model.UserDao;

import dagger.Module;
import dagger.Provides;

@Module
public class RecentUsersModule {
    @Provides
    @RecentUsersScope
    public IRecentUsersRepo provideIRecentUsersRepo(UserDao dao) {
        return new RecentUsersRepo(dao);
    }

    @Provides
    @RecentUsersScope
    public IRecentUsersInteractor provideIRecentUsersInteractor(IRecentUsersRepo repo) {
        return new RecentUsersInteractor(repo);
    }
}
