package com.nancy.recyclerwithviewmodel.API;

import com.nancy.recyclerwithviewmodel.model.UserList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIService {

    @GET("text/first.json")
    Observable<UserList> getUserList();
}
