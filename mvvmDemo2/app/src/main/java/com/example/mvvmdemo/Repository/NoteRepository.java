package com.example.mvvmdemo.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mvvmdemo.Dao.NoteDao;
import com.example.mvvmdemo.Dao.NoteDatabase;
import com.example.mvvmdemo.model.Note;
import com.example.mvvmdemo.model.Todo;
import com.example.mvvmdemo.service.ApiClient;
import com.example.mvvmdemo.service.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    ApiInterface apiInterface;
    private static final String TAG = "MainActivity";

    private LiveData<Note> getNote;
    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }
    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
//    public void deleteid(Note note) {
//        new DeleteAsyncTask(noteDao).execute(note);
//    }

    public LiveData<Note>   getNote(String  id){
        return noteDao.getNote(id);
    }
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;
        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

//    private class DeleteAsyncTask extends AsyncTask<Note,Void,Void> {
//        private NoteDao noteDao;
//        private DeleteAsyncTask(NoteDao noteDao) {
//            this.noteDao=noteDao;
//        }
//
//
//
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            noteDao.deleteid((Note) notes[0]);
//            return null;
//        }
//    }

    ///////////////////////////////////////////////////***********Service*********** //////////////////////////////////////////////////////

    public  void getTodos(){
        Call<List<Todo>> call = apiInterface.getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
               List<Todo> data=response.body();

               try{
                   for(Todo h:data){


                       Note note=new Note(h.getId()+"",h.getUserId()+"",h.getTitle(),h.isCompleted()+"");
                       insert(note);

                   }
                   Log.e(TAG, "onResponse: " +  response.body());

               }catch (Exception e){}

            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });
    }
    public  void getTodo( int id){
        Call<Todo> todoCall = apiInterface.getTodo(id);

        todoCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.e(TAG, "onResponse: " + response.body() );
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });
    }
    public  void getTodosUsingQuery(){
        Call<List<Todo>> listCall = apiInterface.getTodosUsingQuery(3, false);
        listCall.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.e(TAG, "onResponse: " + response.body() );
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {

            }
        });
    }
    public void postTodo(){
        Todo todo = new Todo(3, "Get me milk sucess", false);

        Call<Todo> todoPostCall = apiInterface.postTodo(todo);
        todoPostCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.e(TAG, "onResponse: " + response.body() );
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {

            }
        });

    }
}