package com.example.diego.parqueosuperuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registro_encargado(View view) {
        startActivity(new Intent(getApplicationContext(), PantallaRegistroEncargados.class));
    }

    public void inicio_reportes(View view) {
        startActivity(new Intent(getApplicationContext(), ActivityReportesCalles.class));
    }

    public void inicio_calles(View view) {
        startActivity(new Intent(getApplicationContext(), ActivityAsignacionCalles.class));
    }


    public void lista_encargados(View view) {
        startActivity(new Intent(getApplicationContext(), ActivityListaEncargados.class));
    }
}
