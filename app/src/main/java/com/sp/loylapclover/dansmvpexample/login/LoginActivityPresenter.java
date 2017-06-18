package com.sp.loylapclover.dansmvpexample.login;

import android.support.annotation.Nullable;

/**
 * Created by Daniel on 17/06/2017.
 */

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    @Nullable
    private LoginActivityMVP.View view; //Help lint as the view can be killed by Android
    private LoginActivityMVP.Model model;

    LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model; //Constructor injection
    }

    @Override
    public void setView(@Nullable LoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {

        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {
                model.createUser(view.getFirstName(), view.getLastName());
                view.showUserSavedMessage();
            }
        }
    }

    @Override
    public void getCurrentUser() {

        User user = model.getUser();

        if (user == null) {
            if (view != null) {
                view.showUserNotAvailable();
            }
        } else {
            if (view != null) {
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }
}
