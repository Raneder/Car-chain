package com.mycompany.web.modelo;

public class Configuracion_De_Localidad {
    private int idconfiglocalidad;
    private Localidad localidad;
    private String nombrecl;
    private int descuentocl;
    private int gananciacl;
    private double recargocl;
    private boolean activocl;

    //CONSTRUCTORES
    public Configuracion_De_Localidad(){

    }
    public Configuracion_De_Localidad(int idconfiglocalidad, Localidad localidad, String nombrecl, int descuentocl,
            int gananciacl, double recargocl, boolean activocl) {
        this.idconfiglocalidad = idconfiglocalidad;
        this.localidad = localidad;
        this.nombrecl = nombrecl;
        this.descuentocl = descuentocl;
        this.gananciacl = gananciacl;
        this.recargocl = recargocl;
        this.activocl = activocl;
    }

    //GETTERS
    public int getIdconfiglocalidad() {
        return idconfiglocalidad;
    }
    public Localidad getLocalidad() {
        return localidad;
    }
    public String getNombrecl() {
        return nombrecl;
    }
    public int getDescuentocl() {
        return descuentocl;
    }
    public int getGananciacl() {
        return gananciacl;
    }
    public double getRecargocl() {
        return recargocl;
    }
    public boolean isActivocl() {
        return activocl;
    }
    
    //SETTERS
    public void setIdconfiglocalidad(int idconfiglocalidad) {
        this.idconfiglocalidad = idconfiglocalidad;
    }
    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
    public void setNombrecl(String nombrecl) {
        this.nombrecl = nombrecl;
    }
    public void setDescuentocl(int descuentocl) {
        this.descuentocl = descuentocl;
    }
    public void setGananciacl(int gananciacl) {
        this.gananciacl = gananciacl;
    }
    public void setRecargocl(double recargocl) {
        this.recargocl = recargocl;
    }
    public void setActivocl(boolean activocl) {
        this.activocl = activocl;
    }

    //METODOS
    
}
