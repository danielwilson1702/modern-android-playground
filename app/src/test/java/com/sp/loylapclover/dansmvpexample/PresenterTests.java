package com.sp.loylapclover.dansmvpexample;

import com.sp.loylapclover.dansmvpexample.login.LoginActivityMVP;
import com.sp.loylapclover.dansmvpexample.login.LoginActivityPresenter;
import com.sp.loylapclover.dansmvpexample.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PresenterTests {

    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View mockView;
    LoginActivityPresenter presenter; //Concrete representation as we need to interact with it directly
    User user;

    @Before
    public void setup(){

        mockLoginModel = mock(LoginActivityMVP.Model.class);
        user = new User("Fox", "Mulder");
        when(mockLoginModel.getUser()).thenReturn(user);

        mockView = mock(LoginActivityMVP.View.class);

        presenter = new LoginActivityPresenter(mockLoginModel);
        presenter.setView(mockView);
    }

    @Test
    public void loadTheUserFromTheRepositoryWhenValidUserIsPresent(){
        //Behaviour driven naming

        when(mockLoginModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockView, times(1)).setFirstName("Fox");
        verify(mockView, times(1)).setLastName("Mulder");

        //verifyZeroInteractions(mockView);
        verify(mockView, never()).showUserNotAvailable();
    }

    @Test
    public void shouldShowErrorMessageWhenUserIsNull(){
        when(mockLoginModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        verify(mockLoginModel, times(1)).getUser();

        verify(mockView, never()).setFirstName("Fox");
        verify(mockView, never()).setLastName("Mulder");

        verify(mockView, times(1)).showUserNotAvailable();
    }

    @Test
    public void shouldCreateErrorMessageIfFieldIsEmpty(){
        when(mockView.getFirstName()).thenReturn("");

        presenter.saveUser();

        verify(mockView, times(1)).getFirstName();
        verify(mockView, never()).getLastName();
        verify(mockView, times(1)).showInputError();

        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("");

        presenter.saveUser();

        verify(mockView, times(2)).getFirstName();
        verify(mockView, times(1)).getLastName();
        verify(mockView, times(2)).showInputError();
    }

    @Test
    public void shouldBeAbleToSaveAValidUser(){
        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("Scully");

        presenter.saveUser();

        verify(mockView, times(2)).getFirstName();
        verify(mockView, times(2)).getLastName();

        verify(mockLoginModel, times(1)).createUser("Dana", "Scully");
        verify(mockView, times(1)).showUserSavedMessage();
    }
}
