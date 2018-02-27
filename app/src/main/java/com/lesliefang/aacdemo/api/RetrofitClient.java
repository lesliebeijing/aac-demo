package com.lesliefang.aacdemo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leslie.fang on 2018-02-27.
 */

public class RetrofitClient {
    private static Retrofit retrofit;

    private RetrofitClient() {

    }

    public static Retrofit retrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
