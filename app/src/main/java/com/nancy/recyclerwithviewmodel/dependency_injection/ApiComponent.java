package com.nancy.recyclerwithviewmodel.dependency_injection;


import com.nancy.recyclerwithviewmodel.MainActivity;

import dagger.Component;
import dagger.Module;

@CustomScope

//now this class would give flowerService as module but suppose it was different differwnt use  the same retorfit i.e NetworkModdule. And using retrofit we can download flower service or User
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    //we can use it either in activity presenter or viewgroup.
    void inject(MainActivity mainActivity);
}
