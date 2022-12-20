package com.example.myapplication.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PlasticType" )
public class PlasticType {
    @PrimaryKey(autoGenerate = true)
    public  int TypeId;
    @ColumnInfo(name = "Type")
    private String Type;
    @ColumnInfo(name = "Scale")
    private String Scale;
    @ColumnInfo(name = "Status", defaultValue = "1")
    private int  Status;


    public PlasticType(String type, String scale) {
        Type = type;
        Scale = scale;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
