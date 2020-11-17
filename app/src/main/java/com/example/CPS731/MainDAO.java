package com.example.CPS731;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDAO {
    @Insert()
    void insert(Calories calories);

    @Delete
    void delete(Calories calories);

    @Delete
    void reset(List<Calories> calories);

    @Query("UPDATE `Calorie table` SET CalorieValue = :svalue WHERE ID = :sID")
    void update(int sID, String svalue);

    @Query("UPDATE `Calorie table` SET CalorieCount = :ivalue WHERE ID = :sID")
    void update2(int sID, int ivalue);
    @Query("SELECT * FROM `Calorie table`")
    List<Calories> getAll();
}