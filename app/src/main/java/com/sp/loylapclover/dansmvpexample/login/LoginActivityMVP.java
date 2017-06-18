package com.sp.loylapclover.dansmvpexample.login;

/**
 * Created by Daniel on 17/06/2017.
 *
 * MVP suffix makes it crystal clear to a code reviewer that this
 * component is using MVP
 */

public interface LoginActivityMVP {

    interface View{

        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSavedMessage();

        void setFirstName(String firstName);
        void setLastName(String lastName);
    }

    interface Presenter{

        void setView(LoginActivityMVP.View view);

        void loginButtonClicked();

        void getCurrentUser();
    }

    interface Model{

        void createUser(String firstName, String lastName);

        User getUser();
    }

}
