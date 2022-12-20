package com.example.myapplication.database.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "PlasticHistory" ,
        foreignKeys = {
                @ForeignKey(entity = PlasticType.class,
                        parentColumns = "TypeId",
                        childColumns = "plasticType",
                        onDelete = CASCADE),
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "userId",
                        childColumns = "histoyUser",
                        onDelete = CASCADE),
        })
public class PlasticHistory {
    @PrimaryKey(autoGenerate = true)
    public  int historyid;
    @ColumnInfo(name = "histoy_user")
    private int histoyUser;
    @ColumnInfo(name = "plastic_type")
    private int plasticType;
    @ColumnInfo(name = "place")
    private String place;
    @ColumnInfo(name = "amount")
    private int amount;
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;
    @ColumnInfo(name = "status", defaultValue = "1")
    private int  status;


    public PlasticHistory(int histoyUser, int plasticType, String place, int amount, byte[] image) {
        this.histoyUser = histoyUser;
        this.plasticType = plasticType;
        this.place = place;
        this.amount = amount;
        this.image = image;
    }

    public int getHistoyUser() {
        return histoyUser;
    }

    public void setHistoyUser(int histoyUser) {
        this.histoyUser = histoyUser;
    }

    public int getPlasticType() {
        return plasticType;
    }

    public void setPlasticType(int plasticType) {
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
}
