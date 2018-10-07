package com.example.diego.parqueosuperuser;

public class Encargado {
    private String calle_activa;
    private String fecha_nac;
    private String nombre;
    private String telefono;

    public String getCalle_activa() {
        return calle_activa;
    }

    public void setCalle_activa(String calle_activa) {
        this.calle_activa = calle_activa;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Encargado(String calle_activa, String fecha_nac, String nombre, String telefono) {
        this.calle_activa = calle_activa;
        this.fecha_nac = fecha_nac;
        this.nombre = nombre;
        this.telefono = telefono;
    }
}
