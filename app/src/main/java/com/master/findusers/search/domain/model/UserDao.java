package com.master.findusers.search.domain.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE name=:name")
    Flowable<User> findByName(String name);

    @Query("SELECT * FROM User")
    Flowable<List<User>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    @Delete
    int delete(User user);

}
