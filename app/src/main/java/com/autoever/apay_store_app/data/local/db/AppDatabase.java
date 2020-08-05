package com.autoever.apay_store_app.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.autoever.apay_store_app.data.local.db.dao.UserDao;
import com.autoever.apay_store_app.data.model.db.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
