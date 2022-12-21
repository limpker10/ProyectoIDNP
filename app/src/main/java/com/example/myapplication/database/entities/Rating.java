package com.example.myapplication.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Rating" )
public class Rating {
    @PrimaryKey(autoGenerate = true)
    public  int  ratingId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;
    @ColumnInfo(name = "status", defaultValue = "1")
    private int  status;

    public Rating(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
