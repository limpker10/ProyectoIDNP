package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.database.entities.User;

public class properties extends Application {

    public User user = new User("","","","");
    private static properties instance;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static properties getInstance() {
        if (instance == null){
            instance = new properties();
        }
        return instance;
    }

    private properties() { }
}
