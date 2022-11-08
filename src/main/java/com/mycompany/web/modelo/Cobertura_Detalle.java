package com.mycompany.web.modelo;

import java.util.ArrayList;

public class Cobertura_Detalle {
    private Cobertura cobertura;
    private ArrayList<Detalle> detalles;

    //CONSTRUCTORES
    public Cobertura_Detalle(){

    }
    public Cobertura_Detalle(Cobertura cobertura, ArrayList<Detalle> detalles) {
        this.cobertura = cobertura;
        this.detalles = detalles;
    }

    //GETTERS
    public Cobertura getCobertura() {
        return cobertura;
    }
    public ArrayList<Detalle> getDetalles() {
        return detalles;
    }

    //SETTERS
    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }
    public void setDetalles(ArrayList<Detalle> detalles) {
        this.detalles = detalles;
    }

    //METODOS
    
}
