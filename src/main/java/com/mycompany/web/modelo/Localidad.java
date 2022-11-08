package com.mycompany.web.modelo;

public class Localidad {
    private int idlocalidad;
    private String descripcionlocalidad;
    private String codigopostal;
    private Provincia provincia;
    
    //CONSTRUCTORES
    public Localidad() {
    
    }
    public Localidad(int idlocalidad, String descripcionlocalidad, String codigopostal, Provincia provincia) {
        this.idlocalidad = idlocalidad;
        this.descripcionlocalidad = descripcionlocalidad;
        this.codigopostal = codigopostal;
        this.provincia = provincia;
    }
    
    //GETTERS
    public int getIdlocalidad() {
        return idlocalidad;
    }
    public String getDescripcionlocalidad() {
        return descripcionlocalidad;
    }
    public String getCodigopostal() {
        return codigopostal;
    }
    public Provincia getProvincia() {
        return provincia;
    }
    
    //SETTERS
    public void setIdlocalidad(int idlocalidad) {
        this.idlocalidad = idlocalidad;
    }
    public void setDescripcionlocalidad(String descripcionlocalidad) {
        this.descripcionlocalidad = descripcionlocalidad;
    }
    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    //METODOS
    
}
