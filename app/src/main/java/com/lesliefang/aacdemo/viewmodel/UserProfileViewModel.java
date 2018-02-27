package com.lesliefang.aacdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.lesliefang.aacdemo.repository.UserRepository;
import com.lesliefang.aacdemo.vo.User;

/**
 * Created by leslie.fang on 2018-02-27.
 */

public class UserProfileViewModel extends ViewModel {
    private LiveData<User> user;
    private UserRepository userRepository;

    public UserProfileViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<User> getUser() {
        user = userRepository.getUser();
        return user;
    }
}
