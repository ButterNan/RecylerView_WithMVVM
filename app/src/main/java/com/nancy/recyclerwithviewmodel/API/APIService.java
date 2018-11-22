package com.nancy.recyclerwithviewmodel.API;

import com.nancy.recyclerwithviewmodel.model.Flower;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIService {

    @GET("/feeds/flowers.json")
    Observable<List<Flower>> getUserList();
}
