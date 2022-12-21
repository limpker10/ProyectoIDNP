package com.example.myapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.database.entities.PlasticHistory;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM PlasticHistory WHERE user LIKE :id")
    List<PlasticHistory> getAllId(int id);

    @Query("SELECT place FROM PlasticHistory WHERE user LIKE :id")
    String [] getAllPlaceById(int id);

    @Query("SELECT plasticType FROM PlasticHistory WHERE user LIKE :id")
    int [] getAllPlasticById(int id);

    @Insert
    void insertAll(PlasticHistory... plasticHistories);

}
