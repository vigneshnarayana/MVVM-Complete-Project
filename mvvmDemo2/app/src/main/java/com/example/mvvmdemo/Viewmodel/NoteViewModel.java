package com.example.mvvmdemo.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmdemo.Repository.NoteRepository;
import com.example.mvvmdemo.model.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;
    private LiveData<Note> getNote;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }
    public void insert(Note note) {
        repository.insert(note);
    }
    public void update(Note note) {
        repository.update(note);
    }
    public void delete(Note note) {
        repository.delete(note);
    }
    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
    public LiveData<Note> getNote(String id){
       return repository.getNote(id);
    }
//    public void deleteNotes(Note note) {
//        repository.deleteid(note);
//    }
//////////////////////////////////////////////////////////
    public  void getTodos(){
        repository.getTodos();
    }
    public  void  getTodo(int id){
        repository.getTodo(id);
    }
    public  void getTodosUsingQuery(){
        repository.getTodosUsingQuery();
    }
    public  void postTodo(){
        repository.postTodo();
    }
}