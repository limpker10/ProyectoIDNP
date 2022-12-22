package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.entities.PlasticType;
import com.example.myapplication.database.entities.User;

import java.util.List;

public class properties extends Application {

    private List<PlasticType> elements;
    private static properties instance;

    public List<PlasticType> getElements() {
        return elements;
    }

    public void setElements(List<PlasticType> elements) {
        this.elements = elements;
    }

    public static properties getInstance(Context context) {
        if (instance == null){
            instance = new properties(context);
        }
        return instance;
    }

    private properties(Context context) {
        elements=AppDataBase.getInstance(context).plasticDao().getAll();
    }
}
