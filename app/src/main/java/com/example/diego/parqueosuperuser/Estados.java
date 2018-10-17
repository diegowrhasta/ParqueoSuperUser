package com.example.diego.parqueosuperuser;

public class Estados {
    public String estado;
    public String parqueo;

    public Estados(){

    }
    public String getEstado(){
        return estado;
    }
    public String getParqueo(){
        return parqueo;
    }

    public Estados(String estado, String parqueo) {
        this.estado=estado;
        this.parqueo=parqueo;

    }

}