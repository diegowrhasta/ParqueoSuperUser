package com.example.diego.parqueosuperuser;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class ActivityReportesCalles extends AppCompatActivity {

    TextView fecha;
    Button calendario;

    Calendar c;
    DatePickerDialog Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_calles);

        fecha= (TextView) findViewById(R.id.fecha);
        calendario= (Button) findViewById(R.id.calendario);

        calendario.setOnClickListener(new View.OnClickListener() { //Extrae la fecha escogida por el usuario
            @Override
            public void onClick(View v) {

                c= Calendar.getInstance();
                int dia= c.get(Calendar.DAY_OF_MONTH);
                int mes= c.get(Calendar.MONTH);
                int anho= c.get(Calendar.YEAR);

                Date= new DatePickerDialog(ActivityReportesCalles.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int mes, int dia) {
                        fecha.setText(dia + "/" + (mes+1) + "/" +year);
                    }
                }, dia, mes, anho);
                Date.show();
            }
        });
    }
}
