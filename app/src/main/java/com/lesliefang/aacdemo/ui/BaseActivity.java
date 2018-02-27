package com.lesliefang.aacdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.lesliefang.aacdemo.R;

/**
 * Created by leslie.fang on 2018-02-27.
 */

public class BaseActivity extends AppCompatActivity {
    protected ProgressBar pb;

    protected void initProcessBar() {
        pb = findViewById(R.id.pb);
    }

    public void showProcessBar() {
        pb.setVisibility(ProgressBar.VISIBLE);
    }

    public void hideProcessBar() {
        pb.setVisibility(ProgressBar.GONE);
    }
}
