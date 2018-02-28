package com.lesliefang.aacdemo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.lesliefang.aacdemo.vo.Resource;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by leslie.fang on 2018-02-28.
 */

public abstract class NetworkBoundResource<T> {
    private Executor executor;
    private MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    public NetworkBoundResource() {
        executor = Executors.newSingleThreadExecutor();
        result.setValue(Resource.loading(null));
        final LiveData<T> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch()) {
                fetchFromNetwork(dbSource);
            } else {
                result.setValue(Resource.success(data));
            }
        });
    }

    private void fetchFromNetwork(final LiveData<T> dbSource) {
        result.addSource(dbSource, data -> result.setValue(Resource.loading(data)));
        loadData().enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, final Response<T> response) {
                result.removeSource(dbSource);
                if (response.isSuccessful()) {
                    executor.execute(() -> {
                        saveToDb(response.body());
                        result.addSource(loadFromDb(), data -> result.setValue(Resource.success(data)));
                    });
                } else {
                    String message = null;
                    if (response.errorBody() != null) {
                        try {
                            message = response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    final String errorMessage = message;
                    result.addSource(dbSource, data -> result.setValue(Resource.error(errorMessage, data)));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                result.removeSource(dbSource);
                result.addSource(dbSource, data -> result.setValue(Resource.error(t.getMessage(), data)));
            }
        });
    }

    protected abstract Call<T> loadData();

    // 默认 true ,子类可复写
    protected boolean shouldFetch() {
        return true;
    }

    protected abstract void saveToDb(T data);

    protected abstract LiveData<T> loadFromDb();

    public LiveData<Resource<T>> getResult() {
        return result;
    }
}
