package com.example.CPS731;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.internal.Asserts;

import java.util.ArrayList;
import java.util.List;

public class dblayout extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<Calories> caloriesList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;
    private EditText DBeditText,DBcal;
    private Button btAdd,btReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dblayout);
        DBeditText = findViewById(R.id.DBEditText);


        DBcal = findViewById(R.id.DBcal);

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

                    String value = (DBeditText.getText().toString().trim());
                    String svalue2 = (DBcal.getText().toString().trim());
                    if(value.matches("")==false){
                        System.out.println("1 " + svalue2);
                        Calories calories = new Calories();
                        calories.setValue(value);
                        database.mainDAO().insert(calories);
                        DBeditText.setText("");
                        caloriesList.clear();


                        caloriesList.addAll(database.mainDAO().getAll());
                        adapter.notifyDataSetChanged();
                    }


                    if(svalue2.matches("")==false) {
                        System.out.println("2 " + svalue2);

                        int value2 = Integer.parseInt(svalue2);
                        Calories calories = new Calories();
                        calories.setValue2(value2);
                        database.mainDAO().insert(calories);
                        DBcal.setText("");
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