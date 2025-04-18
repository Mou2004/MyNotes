package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class single_list_view extends AppCompatActivity {
    TextView singleNote;
    String singleNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        singleNote = findViewById(R.id.each_note);
        Intent intent = getIntent();
        singleNoteContent = intent.getStringExtra("Note_Content");
        singleNote.setText(singleNoteContent);


    }
    public void backToHomepage(View view){
        Intent intent_homepage = new Intent(this, MainActivity.class);
        startActivity(intent_homepage);
    }
}