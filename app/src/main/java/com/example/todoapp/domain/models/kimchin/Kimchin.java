package com.example.todoapp.domain.models.kimchin;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Kimchin implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    public Kimchin(String title) {
        this.title = title;
    }
    @Ignore
    public Kimchin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
