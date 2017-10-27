package com.example.sistemas.mapas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sistemas on 27/10/17.
 */

public class Cai {
    @SerializedName("latitud")
    @Expose
    private double latitud;
    @SerializedName("longitud")
    @Expose
    private double longitud;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("ubicacion")
    @Expose
    private String ubicacion;

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}