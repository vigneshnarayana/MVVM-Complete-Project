package com.androidarchitecture.learn.noteapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidarchitecture.learn.noteapplication.Retrofit.RetrofitClientInstance;
import com.androidarchitecture.learn.noteapplication.model.Crop;
import com.androidarchitecture.learn.noteapplication.model.Note;
import com.androidarchitecture.learn.noteapplication.sevice.API;
import com.androidarchitecture.learn.noteapplication.viewmodel.NoteViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity  {


    private NoteViewModel noteViewModel;
    EditText txt1,txt2;
    Button btn,btn2,btn3,btn4;
    TextView vi1,vi2;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);


        btn=findViewById(R.id.btn);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);


        vi1=findViewById(R.id.vi1);
        vi2=findViewById(R.id.vi2);




        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val1=txt1.getText().toString().trim();
                String val2=txt2.getText().toString().trim();

                try{

                    Note note=new Note(val1,val2);
                    noteViewModel.insert(note);

                    Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }

            }
        });


btn2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try{String val1=txt1.getText().toString().trim();
            String val2=txt2.getText().toString().trim();
            Note  note=new Note(val1,val2);
            noteViewModel.update(note);
            Toast.makeText(MainActivity.this, "updated Successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Alread updated", Toast.LENGTH_SHORT).show();
        }
    }
});
btn3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try{
            String val1=txt1.getText().toString().trim();
            String val2=txt2.getText().toString().trim();
            Note  note=new Note(val1,val2);
            noteViewModel.delete(note);
            Toast.makeText(MainActivity.this, "delete Successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

            Toast.makeText(MainActivity.this, "Alread Deleted", Toast.LENGTH_SHORT).show();
        }

    }
});

      btn4.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              try{
                  final String val1=txt1.getText().toString().trim();

                  noteViewModel.getNote(val1).observe(MainActivity.this, new Observer<Note>() {
                      @Override
                      public void onChanged(@Nullable Note note) {
                            vi2.setText("Note :"+note.getNote());

                      }


                  });



                  Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
              }catch (Exception e){
                  Toast.makeText(MainActivity.this, "no data", Toast.LENGTH_SHORT).show();
              }

          }
      });
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                for(Note note:notes){
                    vi1.setText("Id :"+note.getId()+"  Notes:"+note.getNote()+"\n" );
                }



            }
        });

//         retrofit = RetrofitClientInstance.getRetrofitInstance();
//        API api = retrofit.create(API.class);
//
//        Call<List<Crop>> Call = api.getData();
//
//
//        Call.enqueue(new Callback<List<Crop>>() {
//            @Override
//            public void onResponse(retrofit2.Call<List<Crop>> call, Response<List<Crop>> response) {
//                List<Crop> data= response.body();
//               for(Crop i: data){
//                   Log.d("data",i.getCrop() +" --->\n"+i.getCropYear());
//               }
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<List<Crop>> call, Throwable t) {
//
//            }
//        });
//


    }


}
