package com.example.zadanie4_3.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.zadanie4_3.R;
import com.example.zadanie4_3.model.Notebook;
import com.example.zadanie4_3.viewmodel.Adapter;
import com.example.zadanie4_3.viewmodel.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private FloatingActionButton fabAdd;

    private List<Notebook> notesList;

    private DatabaseHelper database = new DatabaseHelper(this);

    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recycler_list);
        fabAdd = findViewById(R.id.fabAdd);
        notesList = new ArrayList<>();
        fetchAllNotes();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, SecondActivity.this, notesList);
        recyclerView.setAdapter(adapter);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, AddNotesActivity.class));
            }
        });
    }

    public void fetchAllNotes() {
        Cursor cursor = database.readNotes();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Заметок нет", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                notesList.add(new Notebook(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }
}