package com.mycompany.web.modelo;

public class Cobertura {
    private int idcobertura;
    private String nombrecobertura;
    private String descripcioncobertura;
    private int recargoporatraso;
    private boolean activocobertura;

    //CONSTRUCTORES
    public Cobertura(){

    }

    //GETTERS
    public int getIdcobertura() {
        return idcobertura;
    }
    public String getNombrecobertura() {
        return nombrecobertura;
    }
    public String getDescripcioncobertura() {
        return descripcioncobertura;
    }
    public int getRecargoporatraso() {
        return recargoporatraso;
    }
    public boolean isActivocobertura() {
        return activocobertura;
    }
        
    //SETTERS
    public void setIdcobertura(int idcobertura) {
        this.idcobertura = idcobertura;
    }
    public void setNombrecobertura(String nombrecobertura) {
        this.nombrecobertura = nombrecobertura;
    }
    public void setDescripcioncobertura(String descripcioncobertura) {
        this.descripcioncobertura = descripcioncobertura;
    }
    public void setRecargoporatraso(int recargoporatraso) {
        this.recargoporatraso = recargoporatraso;
    }
    public void setActivocobertura(boolean activocobertura) {
        this.activocobertura = activocobertura;
    }
    
    //METODOS
    
}
