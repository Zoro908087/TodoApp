package com.example.todoapp.database.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.domain.models.kimchin.Kimchin;

import java.util.List;
@Dao
public interface Room {
    @Query("SELECT * FROM Kimchin")
    List<Kimchin> getAllTasks();
    @Insert
    void addTask(Kimchin kimchin);

    @Update
    void update(Kimchin kimchin);

    @Delete
    void delete (Kimchin kimchin);
}
