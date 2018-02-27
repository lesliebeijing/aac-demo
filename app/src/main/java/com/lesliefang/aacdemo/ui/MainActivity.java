package com.lesliefang.aacdemo.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.TextView;

import com.lesliefang.aacdemo.R;
import com.lesliefang.aacdemo.viewmodel.UserProfileViewModel;

public class MainActivity extends BaseActivity {
    private TextView mUserInfo;
    private UserProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProcessBar();
        mUserInfo = findViewById(R.id.userInfo);

        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.getUser().observe(this, user -> {
            if (user != null) {
                mUserInfo.setText(user.getId() + "\n" + user.getName());
            }
        });
    }
}
