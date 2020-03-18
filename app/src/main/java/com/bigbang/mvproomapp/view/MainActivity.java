package com.bigbang.mvproomapp.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bigbang.mvproomapp.R;
import com.bigbang.mvproomapp.presenter.Contract;
import com.bigbang.mvproomapp.presenter.RoomPresenter;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private Contract.Presenter mainPresenter;

    private ProfileFragment profileFragment;

    @BindView(R.id.username_edittext)
    EditText usernameEditText;

    @BindView(R.id.password_edittext)
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new RoomPresenter(this);
        profileFragment = new ProfileFragment();
    }

    @OnClick(R.id.login_button)
    public void loginClick(View view) {

        String name = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mainPresenter.loginUser(name, password);

    }

    @Override
    public void userLoginSuccess() {

        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.home_frame, profileFragment)
        .commit();

    }

    public String getUsername() {
        return mainPresenter.getUserInstance().getUserName();
    }

    public void logout() {
        mainPresenter.signOutUser();

    }

    @Override
    public void userLoginFailed() {

        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme))
                .setTitle("Login failed")
                .setMessage("Username or password incorrect. Please re-enter.")
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                    }
                })
                .create()
                .show();;

    }

    @Override
    public void userLoggedOut() {

        getSupportFragmentManager()
                .beginTransaction()
                .remove(profileFragment)
                .commit();

    }

    @Override
    public void userNotLoggedInMessage() {

    }

    @Override
    public void displayResults() {

    }
}
