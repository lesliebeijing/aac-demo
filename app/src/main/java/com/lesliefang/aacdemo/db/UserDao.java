package com.lesliefang.aacdemo.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.lesliefang.aacdemo.vo.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by leslie.fang on 2018-02-28.
 */

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user LIMIT 1")
    LiveData<User> load();

    @Query("SELECT COUNT(*) FROM user")
    int getUserCount();

    default boolean userExists() {
        return getUserCount() > 0;
    }
}
