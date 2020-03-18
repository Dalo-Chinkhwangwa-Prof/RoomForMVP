package com.bigbang.mvproomapp.presenter;

import androidx.room.Room;

import com.bigbang.mvproomapp.view.MainActivity;
import com.bigbang.mvproomapp.database.UserEntity;
import com.bigbang.mvproomapp.database.UsersDB;

public class RoomPresenter implements Contract.Presenter {

    private Contract.View mainView;
    private UsersDB usersDB;
    private UserEntity currentUser = null;

    public RoomPresenter(Contract.View mainView) {
        this.mainView = mainView;

        usersDB = Room
                .databaseBuilder(((MainActivity) mainView).getApplicationContext(),
                        UsersDB.class,
                        "users.db"
                )
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public void loginUser(String userName, String password) {
        currentUser = usersDB.getUserDAO().loginSelect(userName, password);

        if(currentUser == null)
            mainView.userLoginFailed();
        else
            mainView.userLoginSuccess();
    }

    @Override
    public void signOutUser() {
        currentUser = null;
        mainView.userLoggedOut();
    }

    @Override
    public UserEntity getUserInstance() {
        return currentUser;
    }

    @Override
    public void getGitResults() {
//TODO: make api call to git
    }
}
