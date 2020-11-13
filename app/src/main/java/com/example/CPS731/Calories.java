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
    private String value;

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
