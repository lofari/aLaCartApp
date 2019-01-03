package com.example.lorenzo.alacarta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurante implements Serializable {

    private String nombre;
    private String direccion;
    private String zona;
    private String especialidad;
    private String precio;
    private List<Secciones> menu;


    public Restaurante(String nombre, String direccion, String zona, String especialidad, String precio, List<Secciones> menu) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.zona = zona;
        this.especialidad = especialidad;
        this.precio = precio;
        this.menu = menu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public List<Secciones> getMenu() {
        return menu;
    }

    public void setMenu(List<Secciones> menu) {
        this.menu = menu;
    }

}
