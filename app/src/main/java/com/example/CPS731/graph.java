package com.example.CPS731;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;


public class graph extends AppCompatActivity {
    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        barChart = (BarChart)  findViewById(R.id.graph);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f,1000));
        barEntries.add(new BarEntry(2f,2000));
        barEntries.add(new BarEntry(3f,2020));
        barEntries.add(new BarEntry(4f,2500));
        barEntries.add(new BarEntry(5f,1500));
        BarDataSet barDataSet= new BarDataSet(barEntries, "Calories");
        ArrayList meal= new ArrayList();
        meal.add("maggi");
        meal.add("noodles");
        meal.add("pizza");
        meal.add("milk");
        meal.add("fires");
        BarData theData = new BarData(barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);




    }

}