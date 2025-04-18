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



public class New_note_detail extends AppCompatActivity {
    Button addNoteButton;
    EditText input;
    ListView previewListOfNotes;
    List<String> notes= new ArrayList<>();
    String[] startList ={"First note", "Second note"};
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_note_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
   public void saveNote(View view) {
       TextView note = findViewById(R.id.Note_input_multiline);
       String ourNote = note.getText().toString();
       //toast is a popup text that appears for a few seconds
       Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();

       Intent intentSaveButton = new Intent(this, MainActivity.class);
       intentSaveButton.putExtra("Note", ourNote);
       startActivity(intentSaveButton);

       // Storing data in SharedPreferences

       SharedPreferences sharedPreferences = getSharedPreferences("UserNote",MODE_PRIVATE);
       SharedPreferences.Editor editor = sharedPreferences.edit();

       // Retrieving user input and saving it
       editor.putString("user_note", note.getText().toString());


       editor.apply();
   }
}
