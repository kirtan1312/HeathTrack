package com.example.CPS731;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class dblayout extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<Calories> caloriesList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;
    private EditText DBeditText;
    private Button btAdd,btReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dblayout);
        DBeditText = findViewById(R.id.DBEditText);
        btAdd = findViewById(R.id.DBbtnAdd);
        btReset = findViewById(R.id.DBbtnReset);
        recyclerView = findViewById(R.id.DBrecyclerView);
        database = RoomDB.getInstance(this);
        caloriesList = database.mainDAO().getAll();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MainAdapter(dblayout.this, caloriesList);
        recyclerView.setAdapter(adapter);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    int value = Integer.parseInt(DBeditText.getText().toString().trim());
                    if(value >-1){
                        Calories calories = new Calories();
                        calories.setValue(value);
                        database.mainDAO().insert(calories);
                        DBeditText.setText("");
                        caloriesList.clear();
                        caloriesList.addAll(database.mainDAO().getAll());
                        adapter.notifyDataSetChanged();
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.mainDAO().reset(caloriesList);
                caloriesList.clear();
                caloriesList.addAll(database.mainDAO().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}