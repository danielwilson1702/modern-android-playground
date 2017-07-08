package com.sp.loylapclover.dansmvpexample.root;

import com.sp.loylapclover.dansmvpexample.login.LoginActivity;
import com.sp.loylapclover.dansmvpexample.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Daniel on 17/06/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity target);
}
