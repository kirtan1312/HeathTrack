package com.example.CPS731;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.internal.Asserts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.YEAR;

public class dblayout extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<Calories> caloriesList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;
    private static int calorieTotal = 0;
    graph gr;
    ProfileActivity prof;
    private EditText DBeditText,DBcal;
    private Button btAdd,btReset,btGraph,btCalendar;
    private  int counter =0;
    TextView totalCals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dblayout);
        DBeditText = findViewById(R.id.DBEditText);


        DBcal = findViewById(R.id.DBcal);
        btCalendar= findViewById(R.id.DBCalendar);
        btAdd = findViewById(R.id.DBbtnAdd);
        btReset = findViewById(R.id.DBbtnReset);
        btGraph = findViewById(R.id.DBgraph);
        recyclerView = findViewById(R.id.DBrecyclerView);
        database = RoomDB.getInstance(this);
        caloriesList = database.mainDAO().getAll();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MainAdapter(dblayout.this, caloriesList);
        gr = new graph();
        prof = new ProfileActivity();
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
                        Log.d("CREATION", "Add value1 ");

                    }


                    if(svalue2.matches("")==false) {
                        //System.out.println("2 " + svalue2);

                        int value2 = Integer.parseInt(svalue2);
                        Calories calories = new Calories();
                        calories.setValue2(value2);
                        database.mainDAO().insert(calories);
                        DBcal.setText("");
                        caloriesList.clear();

                        caloriesList.addAll(database.mainDAO().getAll());
                        adapter.notifyDataSetChanged();

                        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                        calorieTotal = sumTotal(calorieTotal, value2);

                        Log.d("CREATION", "Add value2 " + calorieTotal);
                        i.putExtra("Value",calorieTotal );
                        startActivity(i);
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
        btGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(getApplicationContext(), graph.class);
                startActivity(profileIntent);
            }
        });

        // Calendar ----------------------------- -------------------------------------------
        // Able to change it so that it displays data from database based on day selected
        btCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(dblayout.this, d ,c.get(YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public int sumTotal(int calorieTotal, int value2) {
        calorieTotal += value2;
        return calorieTotal;
    }
//Calendar dialog --------------------------------------------------------------------------------------
    Calendar c = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d= new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthofyear);
            c.set(Calendar.DAY_OF_MONTH,dayofmonth);
        }
    };
}