package com.example.CPS731;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private TextView nameTxtView;
    private TextView  totalCals;
    private ProgressBar progress_bar;
    private  FirebaseAuth mAuth;
    private final String TAG = this.getClass().getName().toUpperCase();

    private int val1;
    private  int val2;

    private static final String USERS = "users";
    private List<Calories> CalorieList;
    Button clrbtn;
     Calories data;
     Context context;

   public ProfileActivity () { }
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        context= getApplicationContext();
        setContentView(R.layout.activity_profile);
        //receive data from login screen
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);
        Log.v("USERID", userRef.getKey());
        totalCals= findViewById(R.id.totalCals);

        clrbtn = findViewById(R.id.Caloriecounter);

        //RoomDB database;

      //  database = RoomDB.getInstance(context);

        nameTxtView = findViewById(R.id.name_textview);
        progress_bar= findViewById(R.id.progress_bar);

        val1 = getIntent().getExtras().getInt("Value");
    //    Calories data = CalorieList.get(val1);

       // MainDAO mViewModel = ;
        //Log.d("LISTSIZEVM",  mViewModel.getDataCount());
        int value = (val1/3000)*100;
        progress_bar.setProgress(value);
        totalCals.setText("Calories: " +  val1);


        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent db = new Intent(getApplicationContext(), dblayout.class);
                startActivity(db);
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.signOut) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
