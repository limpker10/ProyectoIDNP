package com.example.myapplication.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PlasticType" )
public class PlasticType {
    @PrimaryKey(autoGenerate = true)
    public  int TypeId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "color")
    private String color;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "status", defaultValue = "1")
    private int  status;

    public PlasticType(String name, String color, String description) {
        this.name = name;
        this.color = color;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
