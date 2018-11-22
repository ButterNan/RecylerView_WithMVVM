package com.nancy.recyclerwithviewmodel.dependency_injection;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private  String  base_url = " ";

    public NetworkModule(String base_url) {
        this.base_url = base_url;
    }


    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava()
    {
        return  RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGson()
    {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gson,RxJava2CallAdapterFactory rx)
    {
        return new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rx)
                .build();
    }

}
