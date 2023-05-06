package com.example.zadanie4_3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zadanie4_3.R;
import com.example.zadanie4_3.viewmodel.DatabaseHelper;


public class UpdateActivity extends AppCompatActivity {

    private EditText title, description;

    private Button updateNote, deleteNote;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        updateNote = findViewById(R.id.update_note);
        deleteNote = findViewById(R.id.delete_note);

        Intent intent = getIntent();

        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        id = intent.getStringExtra("id");

        updateNote.setOnClickListener(listener);
        deleteNote.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override

        public void onClick(View view) {
            if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                DatabaseHelper database = new DatabaseHelper(UpdateActivity.this);

                if (view.getId() == R.id.update_note) {
                    database.updateNotes(title.getText().toString(), description.getText().toString(), id);
                    return;
                } else if (view.getId() == R.id.delete_note) {
                    database.deleteSingleItem(id);
                    return;
                }

                startActivity(new Intent(UpdateActivity.this, SecondActivity.class));
            } else {
                Toast.makeText(UpdateActivity.this, "Изменений не внесено", Toast.LENGTH_SHORT).show();
            }
        }
    };
}