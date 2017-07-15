package com.sp.loylapclover.intermeditatemvp.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Daniel on 17/06/2017.
 */
@Module
class ApplicationModule {
    private Application application;

    ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return application;
    }
}
