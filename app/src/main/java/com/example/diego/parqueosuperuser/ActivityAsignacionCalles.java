package com.example.diego.parqueosuperuser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ActivityAsignacionCalles extends AppCompatActivity {
    private int seconds = 3;
    EditText tiempo_inicio;
    TextView mostrarestado;
    DatabaseReference databasePruebaHora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion_calles);

        tiempo_inicio = (EditText) findViewById(R.id.tiempoinicio);
        mostrarestado = (TextView) findViewById(R.id.mostrarestado);
        databasePruebaHora = FirebaseDatabase.getInstance().getReference("hora");
        /* */
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
        String hora_actual = ""+mdformat.format(calendar.getTime());
        /*Obtener la hora local */
        //Evento que corre una sola vez para consultar a FireBase algo, la idea es que se aplique esto en el evento de onDataChange del mapa que los encargados estarán viendo, y cuando se
        //haga una reserva osea _Cambie el estado de un nodo_ corra toda esta rutina para establecer un timer que le quite el estado de reservado si las condiciones se cumplen.
      /*  try{
            Query query = FirebaseDatabase.getInstance().getReference().child("hora").child("1"); //Se hace un pequeño Query a la base de datos para poner un puntero en el objeto de encargados
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    hora value = dataSnapshot.getValue(hora.class);
                    String hora = value.getTime();
                    String estado = value.getEstado();
                    mostrarestado.setText(estado); //Muestra el estado de la clase
                    hacerTimer(hora); //Lanza una funcion con la hora para comenzar a hacer un timer que cambiará el valor en firebase
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.e("Error","algo salio mal");
                }
            });
        }
        catch (Exception e)
        {
            Log.e("Error",e.getMessage()+"");
        }
        //
*/


    }/*
    private void hacerTimer(String actual) //Función para hacer correr un timer que expira en 3 segundos y hace un cambio automatico en firebase
    {
        String[] parts = actual.split(":");
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0){
                    seconds--;
                    handler.postDelayed(this,1000);
                    Log.e("Estado",""+seconds);
                }
                else
                {
                    //Timer Complete
                    String id = "1";
                    databasePruebaHora = FirebaseDatabase.getInstance().getReference("hora");
                    databasePruebaHora.child(id).child("estado").setValue("Libre");
                    seconds=3;
                }
            }
        });
    }
    public void set_reserva(View view){ //Boton para poner hora
        String hora = tiempo_inicio.getText().toString();
        String id = "1";
        databasePruebaHora = FirebaseDatabase.getInstance().getReference("hora");
        databasePruebaHora.child(id).setValue(hora);
    }*/

}
