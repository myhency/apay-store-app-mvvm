package com.autoever.apay_store_app.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.autoever.apay_store_app.data.model.db.User;

import io.reactivex.Single;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(User user);

    @Query("SELECT * FROM users WHERE loginId = :loginId LIMIT 1")
    Single<User> findByLoginId(String loginId);
}
