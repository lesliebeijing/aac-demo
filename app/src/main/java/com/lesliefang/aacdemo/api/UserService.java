package com.lesliefang.aacdemo.api;

import com.lesliefang.aacdemo.vo.User;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by leslie.fang on 2018-02-27.
 */

public interface UserService {
    @GET("users/lesliebeijing")
    Call<User> getUser();
}
