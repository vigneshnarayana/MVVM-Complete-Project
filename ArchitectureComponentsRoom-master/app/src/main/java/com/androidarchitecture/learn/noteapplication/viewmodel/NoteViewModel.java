package com.androidarchitecture.learn.noteapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.androidarchitecture.learn.noteapplication.Dao.NoteDao;
import com.androidarchitecture.learn.noteapplication.Dao.NoteRoomDatabase;
import com.androidarchitecture.learn.noteapplication.model.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private NoteDao noteDao;
    private NoteRoomDatabase noteDB;
    private LiveData<List<Note>> mAllNotes;
    private LiveData<Note> getNote;

    public NoteViewModel(Application application) {
        super(application);

        noteDB = NoteRoomDatabase.getDatabase(application);
        noteDao = noteDB.noteDao();
        mAllNotes = noteDao.getAllNotes();

    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }
   public LiveData<Note>   getNote(String  id){
       return noteDao.getNote(id);
    }

    public void update(Note note) {
        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteAsyncTask(noteDao).execute(note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class OperationsAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao mAsyncTaskDao;

        OperationsAsyncTask(NoteDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            return null;
        }
    }

    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(NoteDao mNoteDao) {
            super(mNoteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends OperationsAsyncTask {

        UpdateAsyncTask(NoteDao noteDao) {
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {

        public DeleteAsyncTask(NoteDao noteDao) {
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.delete(notes[0]);
            return null;
        }
    }
}
