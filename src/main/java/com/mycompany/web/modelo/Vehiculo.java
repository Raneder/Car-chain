package com.mycompany.web.modelo;

import java.util.Calendar;

public class Vehiculo {
    private int idvehiculo;
    private Cliente cliente;
    private Version version;
    private String matricula;
    private int añofabricación;
    private String numeromotor;
    private String chasis;
    private boolean gnc;

    //CONSTRUCTORES
    public Vehiculo(){

    }
    public Vehiculo(int idvehiculo, Cliente cliente, Version version, String matricula, int añofabricación,
            String numeromotor, String chasis, boolean gnc) {
        this.idvehiculo = idvehiculo;
        this.cliente = cliente;
        this.version = version;
        this.matricula = matricula;
        this.añofabricación = añofabricación;
        this.numeromotor = numeromotor;
        this.chasis = chasis;
        this.gnc = gnc;
    }

    //GETTERS
    public int getIdvehiculo() {
        return idvehiculo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public Version getVersion() {
        return version;
    }
    public String getMatricula() {
        return matricula;
    }
    public int getAñofabricación() {
        return añofabricación;
    }
    public String getNumeromotor() {
        return numeromotor;
    }
    public String getChasis() {
        return chasis;
    }
    public boolean isGnc() {
        return gnc;
    }

    //SETTERS
    public void setIdvehiculo(int idvehiculo) {
        this.idvehiculo = idvehiculo;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setVersion(Version version) {
        this.version = version;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void setAñofabricación(int añofabricación) {
        this.añofabricación = añofabricación;
    }
    public void setNumeromotor(String numeromotor) {
        this.numeromotor = numeromotor;
    }
    public void setChasis(String chasis) {
        this.chasis = chasis;
    }
    public void setGnc(boolean gnc) {
        this.gnc = gnc;
    }

    //METODOS
    public int calcularAntiguedad(){
        Calendar hoy = Calendar.getInstance();
        return hoy.get(Calendar.YEAR) - this.añofabricación;
    }
    public String obtenerGNC(){
        if(this.gnc){
            return "Si Tiene";
        }
        else{
            return "No Tiene";
        }
    }
}
