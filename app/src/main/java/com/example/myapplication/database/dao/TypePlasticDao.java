package com.example.myapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.database.entities.PlasticType;

import java.util.List;

@Dao
public interface TypePlasticDao {
    @Query("SELECT * FROM PlasticType")
    List<PlasticType> getAll();

    @Query("SELECT name FROM PlasticType")
    String [] getAllName();

    @Insert
    void insertAll(PlasticType... plasticType);

    @Delete
    void delete(PlasticType plasticType);
}
