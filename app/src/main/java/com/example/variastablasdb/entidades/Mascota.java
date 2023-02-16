package com.example.variastablasdb.entidades;

public class Mascota {

    private Integer idDuenio;
    private Integer idMascota;
    private String nombreMascota;
    private String raza;

    public Mascota(){

    }

    public Mascota(Integer idDuenio, Integer idMascota, String nombreMascota, String raza){
        this.idDuenio = idDuenio;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
    }
    public Integer getIdDuenio(){return idDuenio;}

    public void setIdDuenio(Integer idDuenio){ }



}
