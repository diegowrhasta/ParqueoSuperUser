package com.example.diego.parqueosuperuser;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PantallaRegistroEncargados extends AppCompatActivity {

    private EditText telefonoEt, nombreEt, fechaEt, carnetEt, correo,contrasena; // Variables para instanciar los Edit Texts de la actividad

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase BaseFire;
    private DatabaseReference myRef,otroBDD;
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
        correo= (EditText) findViewById(R.id.correo);
        contrasena= (EditText) findViewById(R.id.contrasena);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    /*Funcion que se activa cuando se acciona el boton de resgistrar  */
    public void registrar_encargado(View view) {
        final String nombre = nombreEt.getText().toString().trim();
        final String fecha_nac = fechaEt.getText().toString().trim();
        final String carnet = carnetEt.getText().toString().trim();
        final String telefono = telefonoEt.getText().toString().trim();
        final String email=correo.getText().toString().trim();
        final String password=contrasena.getText().toString().trim();

        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(nombre)||TextUtils.isEmpty(password)||TextUtils.isEmpty(carnet)||TextUtils.isEmpty(fecha_nac)||TextUtils.isEmpty(nombre)){
            Toast.makeText(this,"Ingrese todos los datos",Toast.LENGTH_LONG).show();
            return;
        }
        /*Verificacion de los campos que esten llenos, si no estan llenos pinta de rojo el campo especifico, si todos los ifs son falsos lleva a registrar */
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
        else if (TextUtils.isEmpty(email)) {
            correo.setHintTextColor(getResources().getColor(R.color.colorAccent));
        }
        /*Toma los strings obtenidos de los Edit Text y luego los coloca acorde a la estructura del objeto, el carnet servira como Key */
        else {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){


                                myRef = FirebaseDatabase.getInstance().getReference("Cliente").child(firebaseAuth.getUid()).child("Usuario");
                                myRef.child("nombre").setValue(nombre);
                                myRef.child("fecha_nac").setValue(fecha_nac);
                                myRef.child("telefono").setValue(telefono);
                                myRef.child("calle_activa").setValue("0");
                                myRef.child("carnet").setValue(carnet+"");//Por defecto los encargados tendrán una calle 0 asignada para expresar que no están asignados a ninguna calle
                                myRef.child("correo").setValue(email);
                                myRef.child("password").setValue(password);
                                myRef.child("tipo").setValue("UsuarioEncargado");
                                myRef.child("sector").setValue("0");
                                myRef.child("id").setValue(firebaseAuth.getUid());

                                otroBDD = FirebaseDatabase.getInstance().getReference("Encargado").child(firebaseAuth.getUid());
                                otroBDD.child("nombre").setValue(nombre);
                                otroBDD.child("fecha_nac").setValue(fecha_nac);
                                otroBDD.child("telefono").setValue(telefono);
                                otroBDD.child("calle_activa").setValue("0");
                                otroBDD.child("carnet").setValue(carnet+"");//Por defecto los encargados tendrán una calle 0 asignada para expresar que no están asignados a ninguna calle
                                otroBDD.child("correo").setValue(email);
                                otroBDD.child("sector").setValue("0");
                                otroBDD.child("id").setValue(firebaseAuth.getUid());


                                //se ingresan firebaseAuth.getUid() datos del usuario en la base

                            }else{

                                  Toast.makeText(PantallaRegistroEncargados.this,"Ya existe una cuenta con ese correo o la contraseña es muy corta",Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }
        Toast.makeText(this, "Encargado Agregado con éxito", Toast.LENGTH_SHORT).show();
        // Reset de los campos una vez registrado el encargado
        nombreEt.setText("");
        fechaEt.setText("");
        carnetEt.setText("");
        telefonoEt.setText("");
        correo.setText("");
        contrasena.setText("");
    }

}
