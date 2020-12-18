package com.example.mvvmdemo.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

//    @PrimaryKey(autoGenerate = true)
//    private int id;
    @PrimaryKey
    @NonNull
    private String id;
    private String title;
    private String description;
    private String priority;
    public Note(String id, String title, String description, String priority) {
        this.id=id;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}