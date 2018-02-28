package com.lesliefang.aacdemo.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lesliefang.aacdemo.R;
import com.lesliefang.aacdemo.viewmodel.UserProfileViewModel;
import com.lesliefang.aacdemo.vo.Status;
import com.lesliefang.aacdemo.vo.User;

import static com.lesliefang.aacdemo.vo.Status.LOADING;
import static com.lesliefang.aacdemo.vo.Status.SUCCESS;

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
        viewModel.getUser().observe(this, resource -> {
            Log.d("leslie", "observe " + resource.status);
            Status status = resource.status;
            if (status == LOADING) {
                showProcessBar();
                setData(resource.data);
            } else {
                hideProcessBar();
                if (status == SUCCESS) {
                    setData(resource.data);
                } else {
                    showToast(resource.message);
                }
            }
        });
    }

    private void setData(User user) {
        if (user != null) {
            mUserInfo.setText(user.getId() + "\n" + user.getName());
        }
    }
}
