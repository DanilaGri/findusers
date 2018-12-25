package com.master.findusers.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.master.findusers.db.UserDatabase;
import com.master.findusers.search.domain.model.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    @Singleton
    @Provides
    public UserDatabase provideDb(Context app) {
        return Room.databaseBuilder(app, UserDatabase.class, "user.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public UserDao provideUserDao(UserDatabase db) {
        return db.getUserDao();
    }
}
