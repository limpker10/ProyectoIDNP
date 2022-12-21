package com.example.myapplication.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Rating" )
public class Assistance implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public  int  id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "info")
    private String info;
    @ColumnInfo(name = "color")
    private String color;
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;
    @ColumnInfo(name = "status", defaultValue = "1")
    private int  status;

    public Assistance(String title, String color,String info) {
        this.title = title;
        this.info = info;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}