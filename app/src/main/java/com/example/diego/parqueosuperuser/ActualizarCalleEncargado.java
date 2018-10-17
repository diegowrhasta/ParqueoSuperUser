package com.example.diego.parqueosuperuser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActualizarCalleEncargado extends AppCompatActivity {
    private TextView Veractual, Viewnombre,Verestado;
    private EditText NewCalle, NewSector;
    private DatabaseReference myRef,otroBDD;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_calle_encargado);
        Veractual = (TextView) findViewById(R.id.calleactual);
        Viewnombre = (TextView) findViewById(R.id.usuario);
        NewCalle = (EditText) findViewById(R.id.calle);
        NewSector = (EditText) findViewById(R.id.sector);
        Verestado= (TextView) findViewById(R.id.actualestado);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String calleACtual = intent.getStringExtra("calle_actual");
        String Uid = intent.getStringExtra("id");
        String sector1 = intent.getStringExtra("sector");

        Viewnombre.setText("Usuario: "+nombre);
        Veractual.setText("Calle actual:  "+calleACtual);
        Verestado.setText("Sector:  "+sector1);
    }
    public void registroNew(View view){

        Intent intent = getIntent();
        String Uid = intent.getStringExtra("id");
        final String calle = NewCalle.getText().toString().trim();
        final String sector = NewSector.getText().toString().trim();
        otroBDD = FirebaseDatabase.getInstance().getReference("Encargado").child(Uid);
        otroBDD.child("calle_activa").setValue(calle);
        otroBDD.child("sector").setValue(sector);
        finish();
        Toast.makeText(this, "Calle asignada!", Toast.LENGTH_SHORT).show();



        Intent ListSong = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(ListSong);
        finish();
    }
}
