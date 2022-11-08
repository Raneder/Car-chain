package com.mycompany.web.modelo;

public class Version {
    private int idversion;
    private String nombreversion;
    private String descripcionversion;
    private double preciomercado;
    private double preciomercadognc;
    private Modelo modelo;

    //CONSTRUCTORES
    public Version(){
        
    }
    public Version(int idversion, String nombreversion, String descripcionversion, double preciomercado,
            double preciomercadognc, Modelo modelo) {
        this.idversion = idversion;
        this.nombreversion = nombreversion;
        this.descripcionversion = descripcionversion;
        this.preciomercado = preciomercado;
        this.preciomercadognc = preciomercadognc;
        this.modelo = modelo;
    }
    
    //GETTERS
    public int getIdversion() {
        return idversion;
    }
    public String getNombreversion() {
        return nombreversion;
    }
    public String getDescripcionversion() {
        return descripcionversion;
    }
    public double getPreciomercado() {
        return preciomercado;
    }
    public double getPreciomercadognc() {
        return preciomercadognc;
    }
    public Modelo getModelo() {
        return modelo;
    }
    
    //SETTERS
    public void setIdversion(int idversion) {
        this.idversion = idversion;
    }
    public void setNombreversion(String nombreversion) {
        this.nombreversion = nombreversion;
    }
    public void setDescripcionversion(String descripcionversion) {
        this.descripcionversion = descripcionversion;
    }
    public void setPreciomercado(double preciomercado) {
        this.preciomercado = preciomercado;
    }
    public void setPreciomercadognc(double preciomercadognc) {
        this.preciomercadognc = preciomercadognc;
    }
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    //METODOS    
    
}
