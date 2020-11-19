package com.example.CPS731;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class graph extends AppCompatActivity {
    private ArrayList<Integer> cal = new ArrayList<>();
    private List<Calories> CalorieList;
    BarChart barChart;
    private int pos;
    public graph(){}
    public graph( List<Calories> CalorieList,int i){

        this.CalorieList = CalorieList;
        pos =i;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


//dataSet();


    }
    public void update(int i) {
        Log.d("CREATION", "calling update graph ");
        cal.add(i);
       // System.out.println(i + " "  );
       // System.out.println(cal.size() + " "  );
        dataSet();
    }
    public void dataSet(){

        barChart = (BarChart)  findViewById(R.id.graph);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        Calories data;
        float val=100;
        // float v;
        float counter =0;
        for (int j=0; j<10; j++) {
            //  Calories d = CalorieList.get(j);
            //val= d.getValue2();
            //   val = cal.get(j);
            val= val+100;

          //  System.out.println( "THE VALUE is: "+ j +" and cal "+ val);
            counter++;

            barEntries.add(new BarEntry(val,j));
        }

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