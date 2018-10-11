package com.example.diego.parqueosuperuser;

public class Parqueo {
    private String estado;
    private String placa;
    private String espacio;


    public Parqueo() { /*Se requiere de un constructor vacío por sintaxis, de no tenerlo la aplicación hará crash*/
    }

    public Parqueo(String estado, String placa, String espacio) {
        this.estado = estado;
        this.placa = placa;
        this.espacio = espacio;
    }

    public String getEstado() {
        return estado;
    }

    public String getPlaca() {
        return placa;
    }

    public String getEspacio() {
        return espacio;
    }
}
