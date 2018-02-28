package com.lesliefang.aacdemo.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.lesliefang.aacdemo.api.RetrofitClient;
import com.lesliefang.aacdemo.api.UserService;
import com.lesliefang.aacdemo.db.AppDatabase;
import com.lesliefang.aacdemo.db.UserDao;
import com.lesliefang.aacdemo.vo.Resource;
import com.lesliefang.aacdemo.vo.User;

import retrofit2.Call;

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

    public LiveData<Resource<User>> getUser() {
        return new NetworkBoundResource<User>() {

            @Override
            protected Call<User> loadData() {
                return userService.getUser();
            }

            @Override
            protected void saveToDb(User user) {
                userDao.save(user);
            }

            @Override
            protected LiveData<User> loadFromDb() {
                return userDao.load();
            }
        }.getResult();
    }
}
