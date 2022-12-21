package com.example.myapplication.database;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.R;
import com.example.myapplication.database.dao.UserDao;
import com.example.myapplication.database.entities.User;

@Database(entities = {User.class}, version = 2_1_2 )
public abstract class AppDataBase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "Plastic.db";
    private static AppDataBase sInstance;
    public abstract UserDao userDao();


    public static AppDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.person1);
                byte[] image=BitmapManager.bitmapToByte(bitmap);
                User a = new User("jose","cano","jcanov@unsa.edu.pe","vd9mnzbd");
                a.setImage(image);
                sInstance.userDao().insertAll(a);
            }
        }
        return sInstance;
    }
}
