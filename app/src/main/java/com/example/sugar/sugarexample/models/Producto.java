package com.example.sugar.sugarexample.models;

import com.orm.SugarRecord;

/**
 * Created by Chamu on 03/04/2016.
 */
public class Producto extends SugarRecord {
    private String descripcion;
    private int cantidad;

    public Producto(){
    }

    @Override
    public String toString(){
        return "" +
                descripcion  +
                " : " + cantidad + "\n";
    }

    public Producto( String descripcion, Integer cantidad){
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

