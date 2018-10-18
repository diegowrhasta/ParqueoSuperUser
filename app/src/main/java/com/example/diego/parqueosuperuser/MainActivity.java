package com.example.diego.parqueosuperuser;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void registro_encargado(View view) { //Lanza la actividad para registro
        startActivity(new Intent(getApplicationContext(), PantallaRegistroEncargados.class));
    }

    public void inicio_reportes(View view) { //Lanza la actividad para reportes
        startActivity(new Intent(getApplicationContext(), ActivityReportesCalles.class));
    }


    public void lista_encargados(View view) { //Lanza la actividad para revisar el listado de encargados
        startActivity(new Intent(getApplicationContext(), ActivityListaEncargados.class));
    }
}
