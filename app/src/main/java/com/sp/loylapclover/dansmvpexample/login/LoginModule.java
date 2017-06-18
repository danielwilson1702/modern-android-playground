package com.sp.loylapclover.dansmvpexample.login;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Daniel on 17/06/2017.
 */

@Module
public class LoginModule {

    @Provides
    LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model){
        return new LoginActivityPresenter(model);
    }

    @Provides
    LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository){
        return new LoginModel(repository);
    }

    @Provides
    LoginRepository provideLoginRepository(){
        return new MemoryRepository();
    }

}
