package com.mycompany.web.modelo;

public class Provincia {
    private int idprovincia;
    private String descripcionprovincia;
    
    //CONSTRUCTORES 
    public Provincia(){
        
    }
    public Provincia(int idprovincia, String descripcionprovincia) {
        this.idprovincia = idprovincia;
        this.descripcionprovincia = descripcionprovincia;
    }

    //GETTERS
    public int getIdprovincia() {
        return idprovincia;
    }
    public String getDescripcionprovincia() {
        return descripcionprovincia;
    }
    
    //SETTERS
    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }
    public void setDescripcionprovincia(String descripcionprovincia) {
        this.descripcionprovincia = descripcionprovincia;
    }

    //METODOS

}
