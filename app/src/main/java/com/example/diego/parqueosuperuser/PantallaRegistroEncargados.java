package com.example.diego.parqueosuperuser;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PantallaRegistroEncargados extends AppCompatActivity {

    private EditText telefonoEt, nombreEt, fechaEt, carnetEt; // Variables para instanciar los Edit Texts de la actividad

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase BaseFire;
    private DatabaseReference myRef;
    //Para mostrar el gif de progreso instanciar
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro_encargados);
        /*Programàticamente las variables son asignadas a sus contrapartes en XML */
        telefonoEt = (EditText) findViewById(R.id.telfEt);
        nombreEt = (EditText) findViewById(R.id.nombreEt);
        fechaEt = (EditText) findViewById(R.id.fechaEt);
        carnetEt = (EditText) findViewById(R.id.carnetEt);
        progressDialog = new ProgressDialog(this);

    }
    /*Funcion que se activa cuando se anuncia el  */
    public void registrar_encargado(View view) {
        String nombre = nombreEt.getText().toString().trim();
        String fecha_nac = fechaEt.getText().toString().trim();
        String carnet = carnetEt.getText().toString().trim();
        String telefono = telefonoEt.getText().toString().trim();
        /*Verificacion de los campos que esten llenos, si no estan llenos pinta de rojo el campao especifico, si todos los ifs son falsos lleva a registrar */
        if (TextUtils.isEmpty(nombre)) {
            nombreEt.setHintTextColor(getResources().getColor(R.color.colorAccent));
        }
        else if (TextUtils.isEmpty(fecha_nac)) {
            fechaEt.setHintTextColor(getResources().getColor(R.color.colorAccent));
        }
        else if (TextUtils.isEmpty(carnet)) {
            carnetEt.setHintTextColor(getResources().getColor(R.color.colorAccent));
        }

        else if (TextUtils.isEmpty(telefono)) {
            telefonoEt.setHintTextColor(getResources().getColor(R.color.colorAccent));
        }
        /*Toma los strings obtenidos de los Edit Text y luego los coloca acorde a la estructura del objeto, el carnet servira como Key */
        else {
            myRef = FirebaseDatabase.getInstance().getReference("Encargado");
            myRef.child(carnet).child("nombre").setValue(nombre);
            myRef.child(carnet).child("fecha_nac").setValue(fecha_nac);
            myRef.child(carnet).child("telefono").setValue(telefono);
        }
    }

}
