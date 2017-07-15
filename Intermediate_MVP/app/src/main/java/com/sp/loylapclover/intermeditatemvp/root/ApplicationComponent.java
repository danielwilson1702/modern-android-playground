package com.sp.loylapclover.intermeditatemvp.root;

import com.sp.loylapclover.intermeditatemvp.http.ApiModuleForInfo;
import com.sp.loylapclover.intermeditatemvp.http.ApiModuleForName;
import com.sp.loylapclover.intermeditatemvp.topmovies.TopMoviesActivity;
import com.sp.loylapclover.intermeditatemvp.topmovies.TopMoviesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Daniel on 17/06/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModuleForName.class, ApiModuleForInfo.class, TopMoviesModule.class})
public interface ApplicationComponent {

    void inject(TopMoviesActivity target);
}
