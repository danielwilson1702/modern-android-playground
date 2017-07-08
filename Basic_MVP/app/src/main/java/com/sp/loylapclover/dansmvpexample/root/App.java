package com.sp.loylapclover.dansmvpexample.root;

import android.app.Application;

import com.sp.loylapclover.dansmvpexample.login.LoginModule;

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
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
