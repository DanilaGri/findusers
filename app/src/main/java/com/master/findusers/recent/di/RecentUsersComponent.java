package com.master.findusers.recent.di;

import com.master.findusers.di.scopes.RecentUsersScope;
import com.master.findusers.recent.presentation.RecentUsersPresenter;

import dagger.Subcomponent;

@RecentUsersScope
@Subcomponent(modules = {RecentUsersModule.class})
public interface RecentUsersComponent {
    void inject(RecentUsersPresenter mRecentUsersPresenter);
}
