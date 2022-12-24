package com.example.myapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.database.entities.PlasticHistory;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface HistoryDao {

    @Query("SELECT * FROM PlasticHistory WHERE user LIKE :id")
    List<PlasticHistory> getAllId(int id);

    @Query("SELECT place FROM PlasticHistory WHERE user LIKE :id")
    List <String> getAllPlaceById(int id);

    @Query("SELECT SUM(amount) FROM PlasticHistory WHERE user LIKE :id")
    int getAmountPlasticAll(int id);

    @Query("SELECT SUM(amount) FROM PlasticHistory WHERE plasticType LIKE :type")
    int getAmountByPlastic(String type);

    @Query("SELECT amount FROM PlasticHistory WHERE user LIKE :id")
    List <Integer> getAllAmountById(int id);

    @Query("SELECT month FROM PlasticHistory WHERE user LIKE :id")
    List<String> getDatamonth(int id);

    @Query("SELECT year FROM PlasticHistory WHERE user LIKE :id")
    List<String> getDatayear(int id);

    @Query("SELECT plasticType FROM PlasticHistory WHERE user LIKE :id")
    List<String>  getAllPlasticById(int id);

    @Insert
    void insertAll(PlasticHistory... plasticHistories);

}
