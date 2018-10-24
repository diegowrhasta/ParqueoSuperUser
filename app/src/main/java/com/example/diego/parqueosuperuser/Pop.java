package com.example.diego.parqueosuperuser;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.firebase.ui.database.FirebaseListOptions;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pop extends AppCompatActivity {
    PieChart pieChart;
    private static String TAG = "MainActivity";
    private double libres=0, ocupados=0, reservados=0;
    private String[] xData = {"Libres %", "Ocupados %", "Reservados %" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*.6));
        Query query = FirebaseDatabase.getInstance().getReference("Cliente").child("Parqueo").child("Calle_2").child("Sector_A"); //Se hace un pequeño Query a la base de datos para poner un puntero en el objeto de encargados
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Estados value = dataSnapshot1.getValue(Estados.class);
                    if(value.getEstado().equals("Libre")){
                        libres++;
                    }else if(value.getEstado().equals("Reservado")){
                        reservados++;
                    }else if(value.getEstado().equals("Ocupado")){
                        ocupados++;
                    }
                }
                pieChart = (PieChart) findViewById(R.id.pieChart);
                pieChart.setRotationEnabled(true);
                pieChart.setHoleRadius(25f);
                pieChart.setTransparentCircleAlpha(0);
                pieChart.setCenterText("");
                pieChart.setCenterTextSize(10);
                pieChart.setDrawEntryLabels(true);
                Description description = new Description();
                description.setText("Gráfico de Torta Estado de Autos");
                pieChart.setDescription(description);
                float[] yData={(float)(libres/12*100.00),(float)(ocupados/12 *100.00),(float)(reservados/12*100)};
                addDataSet(yData);
                libres=0;
                reservados=0;
                ocupados=0;
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
    private void addDataSet(float[] yData) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();

        for(int i=0; i<yData.length;i++){
            yEntrys.add(new PieEntry(yData[i], xData[i]));
        }
        //Create DataSet
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Estado Autos");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //Add colors to data set
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);

        pieDataSet.setColors(colors);


        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
