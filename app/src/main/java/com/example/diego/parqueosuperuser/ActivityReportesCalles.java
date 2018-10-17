package com.example.diego.parqueosuperuser;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityReportesCalles extends AppCompatActivity {

    TextView fecha;
    Button calendario;
    ListView listaReporte;
    FirebaseListAdapter adapter;
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



        //Mostrar en listview
        listaReporte = (ListView) findViewById(R.id.listview);
        Query query = FirebaseDatabase.getInstance().getReference().child("Cliente").child("Parqueo").child("Calle_2").child("Sector_A"); //Se hace un pequeño Query a la base de datos para poner un puntero en el objeto de encargados
        FirebaseListOptions<Parqueo> options = new FirebaseListOptions.Builder<Parqueo>() //Este parámetro es el que nutre al Adaptador de Firebase para colocar los valores del Query
                .setLayout(R.layout.reporte)
                .setQuery(query,Parqueo.class)
                .build();
        adapter = new FirebaseListAdapter(options) { //El adaptador de Firebase para aplicarlo al item personalizado que hicimos: encargado.xml para los items del el listview en ListaEncargados
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) { //Esta funcion comienza a llenar los cambos del list view
                TextView estado = v.findViewById(R.id.estado); /*Primero asigna los objectos con sus identificadores de encargado.xml */


                Parqueo enc = (Parqueo) model; //El modelo es el cual se obtiene desde Firebase; del objeto: Con sus atributos, acá es donde entra la parte de case sensitive, si los nombres
                // de la clase local y los nombres en firebase difieren surgirá un error.
                estado.setText("CALLE 2  \nSECTOR A\nNúmero de estacionamiento: "+enc.getEspacio().toString()+"\nEstado: "+enc.getEstado().toString()+"     Placa: "+enc.getPlaca().toString()); /*Todos los valores los estamos manejando como Strings para facilitar el uso */

            }
        };
        listaReporte.setAdapter(adapter); /*Una vez todos los valores se obtienen se lo aplican al listview que tenemos en la actividad */

    }
  @Override
protected void onStart() { /*Metodos necesarios para que la funcion populateView pueda ser llamada y corra el llenado estando en la actividad, y cuando se salga pare con la función de igual manera*/
    super.onStart();
    adapter.startListening();
}
    //List changes okay
    //Check
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    public void generarReporte(View view){
        startActivity(new Intent(getApplicationContext(), Pop.class));
    }
}
