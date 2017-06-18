package com.sp.loylapclover.dansmvpexample.login;

/**
 * Created by Daniel on 17/06/2017.
 */

public interface LoginRepository {

    User getUser();
    void saveUser(User user);
}
