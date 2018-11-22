package com.nancy.recyclerwithviewmodel.application;

import android.app.Application;

import com.nancy.recyclerwithviewmodel.dependency_injection.ApiComponent;
import com.nancy.recyclerwithviewmodel.dependency_injection.DaggerApiComponent;
import com.nancy.recyclerwithviewmodel.dependency_injection.DaggerNetworkComponent;
import com.nancy.recyclerwithviewmodel.dependency_injection.NetworkComponent;
import com.nancy.recyclerwithviewmodel.dependency_injection.NetworkModule;


public class FlowerApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency(){

         mApiComponent  = DaggerApiComponent.builder()
                 .networkComponent(getNetworkComponent())
                 .build();

        }

        public NetworkComponent getNetworkComponent()
        {
           return DaggerNetworkComponent.builder()
                    .networkModule(new NetworkModule("http://services.hanselandpetal.com"))
                    .build();

        }

        //this is written so that we can use it to inject it in our main activity
    public ApiComponent getmApiComponent() {
        return mApiComponent;
    }
}

