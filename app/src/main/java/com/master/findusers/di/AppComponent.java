package com.master.findusers.di;

import com.master.findusers.detail.di.DetailComponent;
import com.master.findusers.detail.di.DetailModule;
import com.master.findusers.di.modules.AppModule;
import com.master.findusers.di.modules.DataModule;
import com.master.findusers.di.modules.NetworkModule;
import com.master.findusers.recent.di.RecentUsersComponent;
import com.master.findusers.recent.di.RecentUsersModule;
import com.master.findusers.search.di.SearchComponent;
import com.master.findusers.search.di.SearchModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, DataModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {
    SearchComponent plusSearchModule(SearchModule searchModule);

    DetailComponent plusDetailModule(DetailModule mDetailModule);

    RecentUsersComponent plusRecentUsersModule(RecentUsersModule mRecentUsersModule);
}
