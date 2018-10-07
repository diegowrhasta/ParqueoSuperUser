package com.example.diego.parqueosuperuser;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

import java.util.List;
/*Cambiando el SDK y demás settings como el support del Project Graddle y de la App Gradle, para que Firebase 4.2.0 pueda trabajar en versiones recomendadas compatibles se elabora
* este programa: */

public class ActivityListaEncargados extends AppCompatActivity {
    ListView listaEncargados;
    FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_encargados);
        listaEncargados = (ListView) findViewById(R.id.listview);
        Query query = FirebaseDatabase.getInstance().getReference().child("Encargado"); //Se hace un pequeño Query a la base de datos para poner un puntero en el objeto de encargados
        FirebaseListOptions<Encargado> options = new FirebaseListOptions.Builder<Encargado>() //Este parámetro es el que nutre al Adaptador de Firebase para colocar los valores del Query
                .setLayout(R.layout.encargado)
                .setQuery(query,Encargado.class)
                .build();
        adapter = new FirebaseListAdapter(options) { //El adaptador de Firebase para aplicarlo al item personalizado que hicimos: encargado.xml para los items del el listview en ListaEncargados
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) { //Esta funcion comienza a llenar los cambos del list view
                TextView calle_activa = v.findViewById(R.id.calle_activa); /*Primero asigna los objectos con sus identificadores de encargado.xml */
                TextView fecha_nac = v.findViewById(R.id.fecha_nac);;
                TextView nombre = v.findViewById(R.id.nombre);;
                TextView telefono = v.findViewById(R.id.telefono);;

                Encargado enc = (Encargado) model; //El modelo es el cual se obtiene desde Firebase; del objeto: Con sus atributos, acá es donde entra la parte de case sensitive, si los nombres
                // de la clase local y los nombres en firebase difieren surgirá un error.
                calle_activa.setText("Calle Activa: "+enc.getCalle_activa().toString()); /*Todos los valores los estamos manejando como Strings para facilitar el uso */
                nombre.setText("Nombre: "+enc.getNombre().toString());
                fecha_nac.setText("Fecha de Nacimiento: "+enc.getFecha_nac().toString());
                telefono.setText("Teléfono: "+enc.getTelefono().toString());
            }
        };
        listaEncargados.setAdapter(adapter); /*Una vez todos los valores se obtienen se lo aplican al listview que tenemos en la actividad */
    }

    @Override
    protected void onStart() { /*Metodos necesarios para que la funcion populateView pueda ser llamada y corra el llenado estando en la actividad, y cuando se salga pare con la función de igual manera*/
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
