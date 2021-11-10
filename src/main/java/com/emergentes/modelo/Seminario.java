
package com.emergentes.modelo;

import java.sql.Date;

public class Seminario {
    
    private int id;
    private String titulo;
    private String expositor;
    private Date fecha;
    private String hora;
    private int cupo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getExpositor() {
        return expositor;
    }

    public void setExpositor(String expositor) {
        this.expositor = expositor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    @Override
    public String toString() {
        return "Seminario{" + "id=" + id + ", titulo=" + titulo + ", expositor=" + expositor + ", fecha=" + fecha + ", hora=" + hora + ", cupo=" + cupo + '}';
    }

    
    
    
}
