package com.example.diego.parqueosuperuser;

public class hora {
    private String time;
    private String estado;

    public hora(String time, String estado) {
        this.time = time;
        this.estado = estado;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public hora() {
    }
}
