package com.bigbang.mvproomapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bigbang.mvproomapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {


    @BindView(R.id.username_textview)
    TextView userNameTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        String username = ((MainActivity) getContext()).getUsername();
        userNameTextView.setText(getContext().getString(R.string.user_welcome, username));
    }

    @OnClick(R.id.button)
    public void logout(View view) {
        ((MainActivity) getContext()).logout();
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
