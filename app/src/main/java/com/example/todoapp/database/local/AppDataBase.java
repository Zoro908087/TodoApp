package com.example.todoapp.database.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todoapp.domain.models.kimchin.Kimchin;

@Database(entities = {Kimchin.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract Room room();
}
