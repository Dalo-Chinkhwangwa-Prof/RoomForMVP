package com.bigbang.mvproomapp.presenter;

import com.bigbang.mvproomapp.database.UserEntity;

public interface Contract {

    interface Presenter {

        void loginUser(String userName, String password);

        void signOutUser();

        UserEntity getUserInstance();

        void getGitResults();
    }

    interface View {

        void userLoginSuccess();

        void userLoginFailed();

        void userLoggedOut();

        void userNotLoggedInMessage();

        void displayResults(/*TODO: List of git repositories*/);
    }
}
