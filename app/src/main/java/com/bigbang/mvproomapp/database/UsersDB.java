package com.bigbang.mvproomapp.database;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {UserEntity.class})
public abstract class UsersDB extends RoomDatabase {
    public abstract UserDAO getUserDAO();
}
