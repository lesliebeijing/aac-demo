package com.lesliefang.aacdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.lesliefang.aacdemo.repository.UserRepository;
import com.lesliefang.aacdemo.vo.Resource;
import com.lesliefang.aacdemo.vo.User;

/**
 * Created by leslie.fang on 2018-02-27.
 */

public class UserProfileViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UserProfileViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(getApplication());
    }

    public LiveData<Resource<User>> getUser() {
        return userRepository.getUser();
    }
}
