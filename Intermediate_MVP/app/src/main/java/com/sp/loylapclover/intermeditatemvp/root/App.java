package com.sp.loylapclover.intermeditatemvp.root;

import android.app.Application;

import com.sp.loylapclover.intermeditatemvp.http.ApiModuleForInfo;
import com.sp.loylapclover.intermeditatemvp.http.ApiModuleForName;
import com.sp.loylapclover.intermeditatemvp.topmovies.TopMoviesModule;

/**
 * Created by Daniel on 17/06/2017.
 */

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModuleForName(new ApiModuleForName())
                .topMoviesModule(new TopMoviesModule())
                .apiModuleForInfo(new ApiModuleForInfo())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
