package com.sp.loylapclover.dansmvpexample.login;

/**
 * Created by Daniel on 17/06/2017.
 */

public class LoginModel implements LoginActivityMVP.Model {

    private LoginRepository repository;

    LoginModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        repository.saveUser(new User(firstName, lastName));
    }

    @Override
    public User getUser() {
        return repository.getUser();
    }
}
