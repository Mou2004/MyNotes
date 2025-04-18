package com.example.mynotes;

import android.util.Log;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    List<String> notes;
    String selectedNote;
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

        notes= ((MyApplication)this.getApplication()).getNotes();

        //creating adapter to populate the notes into our listview
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, notes);

        //link adapter to listview
        previewListOfNotes.setAdapter(adapter);

        //Feature- click on each listview item to display the entire note..
        // For this we create onclickListener for each item of the list

        previewListOfNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String selectedNote = notes.get(position);
               Toast.makeText(MainActivity.this, "Opening Selected Note",Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(MainActivity.this, single_list_view.class);
               intent.putExtra("Note_Content", selectedNote);
               startActivity(intent);
            }
        });

    }
    protected void onResume(){

        super.onResume();
        Intent writtenNote= getIntent();
        // a null check for intent to make sure that the next intent/activity isn't empty
        if (writtenNote != null) {
            String note = writtenNote.getStringExtra("Note");
            if (note != null && !note.isEmpty()) {
                // TextView newNoteview = findViewById(R.id.textView);
                // newNoteview.setText(note);

                if (!notes.contains(note)) { // Prevent duplicates
                    //adding the input notes to the note list
                    notes.add(note);
                    Collections.sort(notes);
                    adapter.notifyDataSetChanged();
                }
            }
            // Retrieving stored data from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserNote", MODE_PRIVATE);
            String savedNote = sharedPreferences.getString("user_note", "");

        }

    }


    public void launchNewNote(View view){
        //launching a new page when clicking add Note button
        Intent intent_add_note = new Intent(this, New_note_detail.class);
        startActivity(intent_add_note);
    }

}