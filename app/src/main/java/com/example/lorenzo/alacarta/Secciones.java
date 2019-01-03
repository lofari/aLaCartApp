package com.example.lorenzo.alacarta;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Secciones implements Serializable {

    private String name;
    private Map<String, Integer> productos;


    public Secciones(String name, Map<String, Integer> productos) {
        this.name = name;
        this.productos = productos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getProductos() {
        return productos;
    }

    public void setProductos(Map<String, Integer> productos) {
        this.productos = productos;
    }
}
