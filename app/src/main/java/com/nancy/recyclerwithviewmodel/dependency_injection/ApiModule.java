package com.nancy.recyclerwithviewmodel.dependency_injection;


import com.nancy.recyclerwithviewmodel.API.APIService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @CustomScope
    APIService getApiService(Retrofit retro)
    {
        return retro.create(APIService.class);
    }
}
