package com.autoever.apay_store_app.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @ColumnInfo(name = "created_at")
    public String createdAt;

    @PrimaryKey
    public Long id;

    public String loginId;

    public String userName;

    public String password;

    public String phoneNumber;

    @ColumnInfo(name = "updatedAt")
    public String updatedAt;
}
