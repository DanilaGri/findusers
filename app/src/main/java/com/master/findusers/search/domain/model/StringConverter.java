package com.master.findusers.search.domain.model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class StringConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromFilmsList(List<String> list) {
        if (list == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        String json = gson.toJson(list, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<String> toFilmsList(String listString) {
        if (listString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> mFilmsList = gson.fromJson(listString, type);
        return mFilmsList;
    }

}

