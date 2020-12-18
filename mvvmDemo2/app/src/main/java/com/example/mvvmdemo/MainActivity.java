package com.example.mvvmdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.mvvmdemo.Viewmodel.NoteViewModel;
import com.example.mvvmdemo.model.Note;
import com.example.mvvmdemo.model.Todo;
import com.example.mvvmdemo.service.ApiClient;
import com.example.mvvmdemo.service.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
   NoteViewModel noteViewModel;
   EditText txt1;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
///////////////////////////////////////////
        noteViewModel= ViewModelProviders.of(this).get(NoteViewModel.class);

        ///////////////////////////////
        txt1=findViewById(R.id.txt1);

//        Note note=new Note("check2","check2","check2","check2");
//        noteViewModel.insert(note);
         //////////////////////////get all
//        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
//            @Override
//            public void onChanged(List<Note> notes) {
//                  for(Note note1:notes){
//                      note1.getPriority();
//
//                  }
//            }
//        });

        /////////////////////////////////////////////////////////////////////////////////////////////


    }
    public void getTodos(View view) {

             noteViewModel.getTodos();


    }

    public void getTodoUsingRouteParameter(View view) {
          String i=txt1.getText().toString().trim();
        int a=Integer.parseInt(i);
        noteViewModel.getTodo(a);
    }

    public void getTodosUsingQuery(View view) {


        noteViewModel.getTodosUsingQuery();

    }

    public void postTodo(View view) {

       noteViewModel.postTodo();

    }
}