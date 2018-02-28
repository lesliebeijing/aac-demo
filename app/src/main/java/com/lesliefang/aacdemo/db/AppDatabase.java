package com.lesliefang.aacdemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.lesliefang.aacdemo.vo.User;

/**
 * Created by leslie.fang on 2018-02-28.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDb;
    public static final String DB_NAME = "mydb";

    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        if (appDb == null) {
            synchronized (AppDatabase.class) {
                if (appDb == null) {
                    appDb = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
                }
            }
        }
        return appDb;
    }
}
