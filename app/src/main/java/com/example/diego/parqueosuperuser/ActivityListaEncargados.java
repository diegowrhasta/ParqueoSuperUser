package com.example.diego.parqueosuperuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/*Cambiando el SDK y demás settings como el support del Project Graddle y de la App Gradle, para que Firebase 4.2.0 pueda trabajar en versiones recomendadas compatibles se elabora
* este programa: */

public class ActivityListaEncargados extends AppCompatActivity implements SearchView.OnQueryTextListener,ListView.OnClickListener {
    ListView list;
    DatabaseReference ArtistasBBD;
    ListView Verlista1;

    private FirebaseAuth firebaseAuth;
    //List<UserInformation> artistList;
    ArrayList<Encargado> artistList = new ArrayList<Encargado>();


    ListaArtista adapter;  //algo asi creo
    SearchView editsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_encargados);

        ArtistasBBD= FirebaseDatabase.getInstance().getReference("Encargado");



        Verlista1=(ListView) findViewById(R.id.listview); //nombre de donde se muestra



    }

    @Override
    protected void onStart() {
        super.onStart();

        ArtistasBBD.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();
                for (DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Encargado artist= artistSnapshot.getValue(Encargado.class);
                    artistList.add(artist);
                }

                adapter=new ListaArtista(ActivityListaEncargados.this, artistList);
                Verlista1.setAdapter(adapter);


                editsearch = (SearchView) findViewById(R.id.search);
                editsearch.setOnQueryTextListener(ActivityListaEncargados.this);

                Verlista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                     @Override
                                                     public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){
                                                         // TODO Auto-generated method stub
                                                         TextView v = (TextView) view.findViewById(R.id.nombre);
                                                         Toast.makeText(getApplicationContext(), "" + v.getText(), Toast.LENGTH_LONG).show();
                                                         Intent intent = new Intent(getApplicationContext(),ActualizarCalleEncargado.class);
                                                         Encargado selectedProduct = artistList.get(position);

                                                         // Poner el Id de la imagen como extra en la intención
                                                         intent.putExtra("nombre",selectedProduct.getNombre());
                                                         intent.putExtra("sector",selectedProduct.getSector());
                                                         intent.putExtra("calle_actual", selectedProduct.getCalle_activa());
                                                         intent.putExtra("id", selectedProduct.getId());


                                                         startActivity(intent);

                                                         // Aquí pasaremos el parámetro de la intención creada previamente

                                                     }
                                                 }
                );

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);         //falta
        return false;

    }

    @Override
    public void onClick(View v) {

    }
}
