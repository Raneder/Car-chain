package com.mycompany.web.modelo;

public class Modelo {
    private int idmodelo;
    private String nombremodelo;
    private String descripcionmodelo;
    private Marca marca;
    
    //CONSTRUCTORES
    public Modelo(){
        
    }
    public Modelo(int idmodelo, String nombremodelo, String descripcionmodelo, Marca marca) {
        this.idmodelo = idmodelo;
        this.nombremodelo = nombremodelo;
        this.descripcionmodelo = descripcionmodelo;
        this.marca = marca;
    }

    //GETTERS
    public int getIdmodelo() {
        return idmodelo;
    }
    public String getNombremodelo() {
        return nombremodelo;
    }
    public String getDescripcionmodelo() {
        return descripcionmodelo;
    }
    public Marca getMarca() {
        return marca;
    }

    //SETTERS
    public void setIdmodelo(int idmodelo) {
        this.idmodelo = idmodelo;
    }
    public void setNombremodelo(String nombremodelo) {
        this.nombremodelo = nombremodelo;
    }
    public void setDescripcionmodelo(String descripcionmodelo) {
        this.descripcionmodelo = descripcionmodelo;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    //METODOS
    
}
