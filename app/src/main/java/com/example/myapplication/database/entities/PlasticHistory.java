package com.example.myapplication.database.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "PlasticHistory",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "uid",
                        childColumns = "user"),
        })
public class PlasticHistory implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int historyid;

    @ColumnInfo(name = "user")
    private int user;

    @ColumnInfo(name = "plasticType")
    private String plasticType;

    @ColumnInfo(name = "place")
    private String place;

    @ColumnInfo(name = "amount")
    private int amount;

    @ColumnInfo(name = "month")
    private String month;

    @ColumnInfo(name = "year")
    private String year;

    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @ColumnInfo(name = "status", defaultValue = "1")
    private int status;

    public PlasticHistory(int user, String plasticType, String place, int amount, byte[] image) {
        this.user = user;
        this.plasticType = plasticType;
        this.place = place;
        this.amount = amount;
        this.image = image;
    }

    public int getHistoryid() {
        return historyid;
    }


    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getPlasticType() {
        return plasticType;
    }

    public void setPlasticType(String plasticType) {
        this.plasticType = plasticType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
