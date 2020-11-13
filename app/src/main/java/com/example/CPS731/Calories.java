package com.example.CPS731;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Calorie table")
public class Calories implements Serializable {
    @PrimaryKey(autoGenerate = true)
    protected int ID;

    @ColumnInfo(name = "CalorieValue")
    private int value;

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
