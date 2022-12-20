package com.example.myapplication.database;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.database.dao.UserDao;
import com.example.myapplication.database.entities.User;

@Database(entities = {User.class}, version = 2 )
public abstract class AppDataBase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "Plastic.db";
    private static AppDataBase sInstance;
    public abstract UserDao userDao();


    public static AppDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
                sInstance.userDao().insertAll(new User("jose","cano","jcanov@unsa.edu.pe","vd9mnzbd"));
            }
        }
        return sInstance;
    }
}
