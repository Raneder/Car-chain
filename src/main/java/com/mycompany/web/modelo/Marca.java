package com.mycompany.web.modelo;

public class Marca {
    private int idmarca;
    private String nombremarca;
    private String descripcionmarca;

    //CONSTRUCTORES
    public Marca(){
        
    }
    public Marca(int idmarca, String nombremarca, String descripcionmarca) {
        this.idmarca = idmarca;
        this.nombremarca = nombremarca;
        this.descripcionmarca = descripcionmarca;
    }

    //GETTERS
    public int getIdmarca() {
        return idmarca;
    }
    public String getNombremarca() {
        return nombremarca;
    }
    public String getDescripcionmarca() {
        return descripcionmarca;
    }

    //SETTERS
    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }
    public void setNombremarca(String nombremarca) {
        this.nombremarca = nombremarca;
    }
    public void setDescripcionmarca(String descripcionmarca) {
        this.descripcionmarca = descripcionmarca;
    }

    //METODOS
    
}
