package com.example.mvvmdemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmdemo.model.Note;

import java.util.List;

@Dao
public interface NoteDao {
    //insert///
    @Insert
    void insert(Note note);

    ///update///
    @Update
    void update(Note note);

    ///delete////
    @Delete
    void delete(Note note);
//    @Delete
//    int deleteid(Note note);   /// optional////
    @Query("DELETE FROM note_table")
    void deleteAllNotes();



  //// query
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM note_table WHERE id=:noteId")
    LiveData<Note> getNote(String noteId);


}