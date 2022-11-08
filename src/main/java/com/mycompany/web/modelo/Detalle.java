package com.mycompany.web.modelo;

public class Detalle {
    private int iddetalle;
    private String nombredetalle;
    private String descripciondetalle;
    private int porcentajemiles;
    private double montofijo;
    private boolean activodetalle;
    
    //CONSTRUCTORES
    public Detalle(){

    }
    
    //GETTERS
    public int getIddetalle() {
        return iddetalle;
    }
    public String getNombredetalle() {
        return nombredetalle;
    }
    public String getDescripciondetalle() {
        return descripciondetalle;
    }
    public int getPorcentajemiles() {
        return porcentajemiles;
    }
    public double getMontofijo() {
        return montofijo;
    }
    public boolean isActivodetalle() {
        return activodetalle;
    }
    //SETTERS
    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }
    public void setNombredetalle(String nombredetalle) {
        this.nombredetalle = nombredetalle;
    }
    public void setDescripciondetalle(String descripciondetalle) {
        this.descripciondetalle = descripciondetalle;
    }
    public void setPorcentajemiles(int porcentajemiles) {
        this.porcentajemiles = porcentajemiles;
    }
    public void setMontofijo(double montofijo) {
        this.montofijo = montofijo;
    }
    public void setActivodetalle(boolean activodetalle) {
        this.activodetalle = activodetalle;
    }

    //METODOS
    
}
