package com.example.diego.parqueosuperuser;
// Esta es una clase adaptador, para vaciar todos los valores de firebase y luego poder manipularlos
public class Encargado { //Todos estos atributos son los registrados en Firebase, vale recalcar que son Case Sensitive, hay que respetar minúscula de mayúscula para trabajarlos de buena manera
    private String calle_activa;
    private String carnet;
    private String correo;
    private String id;
    private String nombre;
    private String password;
    private String sector;
    private String telefono;
    private String tipo;



    public Encargado() { /*Se requiere de un constructor vacío por sintaxis, de no tenerlo la aplicación hará crash*/
    }

    public Encargado(String calle_activa, String carnet, String correo, String id, String nombre, String password, String sector, String telefono, String tipo) {
        this.calle_activa = calle_activa;
        this.carnet = carnet;
        this.correo = correo;
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.sector = sector;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public String getCalle_activa() {
        return calle_activa;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getCorreo() {
        return correo;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getSector() {
        return sector;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo() {
        return tipo;
    }
}
