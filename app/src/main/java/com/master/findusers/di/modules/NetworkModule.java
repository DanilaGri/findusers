package com.master.findusers.di.modules;

import com.master.findusers.network.API;
import com.master.findusers.network.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public API provideApi() {
        return new RestClient().getApi();
    }
}
