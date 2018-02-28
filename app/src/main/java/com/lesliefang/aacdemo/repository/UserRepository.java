package com.lesliefang.aacdemo.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.lesliefang.aacdemo.api.RetrofitClient;
import com.lesliefang.aacdemo.api.UserService;
import com.lesliefang.aacdemo.db.AppDatabase;
import com.lesliefang.aacdemo.db.UserDao;
import com.lesliefang.aacdemo.vo.User;

import java.io.IOException;
import java.util.concurrent.Executors;

import retrofit2.Response;

/**
 * Created by leslie.fang on 2018-02-27.
 */

public class UserRepository {
    private UserService userService;
    private UserDao userDao;

    public UserRepository(Context context) {
        userService = RetrofitClient.retrofit().create(UserService.class);
        userDao = AppDatabase.getInstance(context).userDao();
    }

    public LiveData<User> getUser() {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (!userDao.userExists()) {
                Log.d("leslie", "not exists fetch from network");
                try {
                    Response<User> response = userService.getUser().execute();
                    userDao.save(response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return userDao.load();
    }
}
