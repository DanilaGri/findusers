package com.master.findusers.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.master.findusers.search.domain.model.User;
import com.master.findusers.search.domain.model.UserDao;

@Database(entities = {User.class}, version = UserDatabase.VERSION)
public abstract class UserDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract UserDao getUserDao();

}