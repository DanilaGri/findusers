package com.master.findusers.base;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.master.findusers.detail.di.DetailComponent;
import com.master.findusers.detail.di.DetailModule;
import com.master.findusers.di.AppComponent;
import com.master.findusers.di.DaggerAppComponent;
import com.master.findusers.di.modules.AppModule;
import com.master.findusers.recent.di.RecentUsersComponent;
import com.master.findusers.recent.di.RecentUsersModule;
import com.master.findusers.search.di.SearchComponent;
import com.master.findusers.search.di.SearchModule;

public class App extends Application {
    public static App INSTANCE;
    private AppComponent mAppComponent;
    private SearchComponent mSearchComponent;
    private DetailComponent mDetailComponent;
    private RecentUsersComponent mRecentUsersComponent;

    public static App getApp() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        super.onCreate();
        INSTANCE = this;

        // Make sure we use vector drawables
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public SearchComponent plusSearchModule(SearchModule searchModule) {
        if (mSearchComponent == null) {
            mSearchComponent = getAppComponent().plusSearchModule(searchModule);
        }
        return mSearchComponent;
    }

    public DetailComponent plusDetailModule(DetailModule detailModule) {
        if (mDetailComponent == null) {
            mDetailComponent = getAppComponent().plusDetailModule(detailModule);
        }
        return mDetailComponent;
    }

    public RecentUsersComponent plusRecentUsersModule(RecentUsersModule recentUsersModule) {
        if (mRecentUsersComponent == null) {
            mRecentUsersComponent = getAppComponent().plusRecentUsersModule(recentUsersModule);
        }
        return mRecentUsersComponent;
    }
}
