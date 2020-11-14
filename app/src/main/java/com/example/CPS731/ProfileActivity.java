package com.example.CPS731;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private TextView nameTxtView,totalCals;
    private ProgressBar progress_bar;

    private final String TAG = this.getClass().getName().toUpperCase();


    private static final String USERS = "users";
    Button clrbtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //receive data from login screen
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);
        Log.v("USERID", userRef.getKey());

        nameTxtView = findViewById(R.id.name_textview);
        progress_bar= findViewById(R.id.progress_bar);
        totalCals= findViewById(R.id.totalCals);
        clrbtn = findViewById(R.id.Caloriecounter);
        int value =90;
        progress_bar.setProgress(value);
        totalCals.setText( value + " Calories");


        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent db = new Intent(getApplicationContext(), dblayout.class);
                startActivity(db);
            }
        });

    }

}
