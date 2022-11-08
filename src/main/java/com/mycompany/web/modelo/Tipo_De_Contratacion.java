package com.mycompany.web.modelo;

public class Tipo_De_Contratacion {
    private int idtipocontratacion;
    private String nombrecontratacion;
    private int cantidadmesescontratacion;

    //CONSTRUCTORES
    public Tipo_De_Contratacion(){
        this.idtipocontratacion = 0;
        this.nombrecontratacion = "";
        this.cantidadmesescontratacion = 0;
    }
    public Tipo_De_Contratacion(int idtipocontratacion, String nombrecontratacion, int cantidadmesescontratacion) {
        this.idtipocontratacion = idtipocontratacion;
        this.nombrecontratacion = nombrecontratacion;
        this.cantidadmesescontratacion = cantidadmesescontratacion;
    }

    //GETTERS
    public int getIdtipocontratacion() {
        return idtipocontratacion;
    }
    public String getNombrecontratacion() {
        if(this.nombrecontratacion.equals("")){
            return "-";
        }
        else{
            return nombrecontratacion;
        }
    }
    public int getCantidadmesescontratacion() {
        return cantidadmesescontratacion;
    }

    //SETTERS
    public void setIdtipocontratacion(int idtipocontratacion) {
        this.idtipocontratacion = idtipocontratacion;
    }
    public void setNombrecontratacion(String nombrecontratacion) {
        this.nombrecontratacion = nombrecontratacion;
    }
    public void setCantidadmesescontratacion(int cantidadmesescontratacion) {
        this.cantidadmesescontratacion = cantidadmesescontratacion;
    }

    //METODOS
    
}
