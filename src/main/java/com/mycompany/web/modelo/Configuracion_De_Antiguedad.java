package com.mycompany.web.modelo;

public class Configuracion_De_Antiguedad {
    private int idconfigantiguedad;
    private String nombreca;
    private int minimaca;
    private int maximaca;
    private int descuentoca;
    private int gananciaca;
    private double recargoca;
    private boolean activoca;

    //CONSTRUCTORES
    public Configuracion_De_Antiguedad(){

    }
    public Configuracion_De_Antiguedad(int idconfigantiguedad, String nombreca, int minimaca, int maximaca,
            int descuentoca, int gananciaca, double recargoca, boolean activoca) {
        this.idconfigantiguedad = idconfigantiguedad;
        this.nombreca = nombreca;
        this.minimaca = minimaca;
        this.maximaca = maximaca;
        this.descuentoca = descuentoca;
        this.gananciaca = gananciaca;
        this.recargoca = recargoca;
        this.activoca = activoca;
    }

    //GETTERS
    public int getIdconfigantiguedad() {
        return idconfigantiguedad;
    }
    public String getNombreca() {
        return nombreca;
    }
    public int getMinimaca() {
        return minimaca;
    }
    public int getMaximaca() {
        return maximaca;
    }
    public int getDescuentoca() {
        return descuentoca;
    }
    public int getGananciaca() {
        return gananciaca;
    }
    public double getRecargoca() {
        return recargoca;
    }
    public boolean isActivoca() {
        return activoca;
    }

    //SETTERS
    public void setIdconfigantiguedad(int idconfigantiguedad) {
        this.idconfigantiguedad = idconfigantiguedad;
    }
    public void setNombreca(String nombreca) {
        this.nombreca = nombreca;
    }
    public void setMinimaca(int minimaca) {
        this.minimaca = minimaca;
    }
    public void setMaximaca(int maximaca) {
        this.maximaca = maximaca;
    }
    public void setDescuentoca(int descuentoca) {
        this.descuentoca = descuentoca;
    }
    public void setGananciaca(int gananciaca) {
        this.gananciaca = gananciaca;
    }
    public void setRecargoca(double recargoca) {
        this.recargoca = recargoca;
    }
    public void setActivoca(boolean activoca) {
        this.activoca = activoca;
    }

    //METODOS
    
}
