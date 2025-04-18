package com.example.mynotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addNoteButton;
    EditText input;
    ListView previewListOfNotes;
    //List<String> notes= new ArrayList<>();
    List<String> notes;
    String[] startList ={"First note", "Second note"};
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        previewListOfNotes = findViewById(R.id.notesPreviewList);
        //notes = new ArrayList<>(Arrays.asList());
        notes= ((MyApplication)this.getApplication()).getNotes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, notes);

        previewListOfNotes.setAdapter(adapter);

        /*notes = new ArrayList<String>(Arrays.asList(startList));
        addNoteButton= findViewById(R.id.button);
        input = findViewById(R.id.editTextText2);
        previewListOfNotes = findViewById(R.id.notesPreviewList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        previewListOfNotes.setAdapter(adapter);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNote = input.getText().toString();
                notes.add(newNote);
                Collections.sort(notes);

                adapter.notifyDataSetChanged();
            }
        });*/


    }
    protected void onResume(){

        super.onResume();
        Intent writteNote= getIntent();
        String note = writteNote.getStringExtra("Note");
        if (note != null && !note.isEmpty()) {
           // TextView newNoteview = findViewById(R.id.textView);
           // newNoteview.setText(note);

            if (!notes.contains(note)) { // Prevent duplicates
                notes.add(note);
                Collections.sort(notes);
                adapter.notifyDataSetChanged();
            }
        }
        // Retrieving stored data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserNotes", MODE_PRIVATE);
        String savedNote = sharedPreferences.getString("user_note", "");


        // Populating EditText fields with stored data
        input.setText(savedNote);



    }


    public void launchNewNote(View view){
        Intent intent_add_note = new Intent(this, New_note_detail.class);
        startActivity(intent_add_note);
    }

}