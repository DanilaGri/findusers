package com.master.findusers.network;

import com.master.findusers.search.domain.model.UsersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("people")
    Observable<UsersResponse> findUsers(@Query("search") String search);
}
