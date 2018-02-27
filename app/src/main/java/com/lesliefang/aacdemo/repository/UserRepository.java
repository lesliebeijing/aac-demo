package com.lesliefang.aacdemo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.lesliefang.aacdemo.api.RetrofitClient;
import com.lesliefang.aacdemo.api.UserService;
import com.lesliefang.aacdemo.vo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by leslie.fang on 2018-02-27.
 */

public class UserRepository {
    private UserService userService;

    public UserRepository() {
        userService = RetrofitClient.retrofit().create(UserService.class);
    }

    public LiveData<User> getUser() {
        final MutableLiveData<User> user = new MutableLiveData<>();
        userService.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return user;
    }
}
