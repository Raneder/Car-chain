package com.mycompany.web.modelo;

public class Configuracion_De_Edad {
    private int idconfigedad;
    private String nombrece;
    private int minimace;
    private int maximace;
    private int descuentoce;
    private int gananciace;
    private double recargoce;
    private boolean activoce;
    
    //CONSTRUCTORES
    public Configuracion_De_Edad(){

    }
    public Configuracion_De_Edad(int idconfigedad, String nombrece, int minimace, int maximace, int descuentoce,
            int gananciace, double recargoce, boolean activoce) {
        this.idconfigedad = idconfigedad;
        this.nombrece = nombrece;
        this.minimace = minimace;
        this.maximace = maximace;
        this.descuentoce = descuentoce;
        this.gananciace = gananciace;
        this.recargoce = recargoce;
        this.activoce = activoce;
    }

    //GETTERS
    public int getIdconfigedad() {
        return idconfigedad;
    }
    public String getNombrece() {
        return nombrece;
    }
    public int getMinimace() {
        return minimace;
    }
    public int getMaximace() {
        return maximace;
    }
    public int getDescuentoce() {
        return descuentoce;
    }
    public int getGananciace() {
        return gananciace;
    }
    public double getRecargoce() {
        return recargoce;
    }
    public boolean isActivoce() {
        return activoce;
    }

    //SETTERS
    public void setIdconfiguracionedad(int idconfigedad) {
        this.idconfigedad = idconfigedad;
    }
    public void setNombrece(String nombrece) {
        this.nombrece = nombrece;
    }
    public void setMinimace(int minimace) {
        this.minimace = minimace;
    }
    public void setMaximace(int maximace) {
        this.maximace = maximace;
    }
    public void setDescuentoce(int descuentoce) {
        this.descuentoce = descuentoce;
    }
    public void setGananciace(int gananciace) {
        this.gananciace = gananciace;
    }
    public void setRecargoce(double recargoce) {
        this.recargoce = recargoce;
    }
    public void setActivoce(boolean activoce) {
        this.activoce = activoce;
    }

    //METODOS
    
}
