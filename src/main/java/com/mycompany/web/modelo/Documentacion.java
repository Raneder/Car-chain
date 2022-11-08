package com.mycompany.web.modelo;

import java.io.InputStream;

public class Documentacion {
    private int iddocumentacion;
    private InputStream fotofrontal;
    private InputStream fototrasera;
    private InputStream fotolateraluno;
    private InputStream fotolateraldos;
    private InputStream fototecho;
    private InputStream cedulaverde;

    //CONSTRUCTORES
    public Documentacion(){
    }

    //GETTERS
    public int getIddocumentacion() {
        return iddocumentacion;
    }
    public InputStream getFotofrontal() {
        return fotofrontal;
    }
    public InputStream getFototrasera() {
        return fototrasera;
    }
    public InputStream getFotolateraluno() {
        return fotolateraluno;
    }
    public InputStream getFotolateraldos() {
        return fotolateraldos;
    }
    public InputStream getFototecho() {
        return fototecho;
    }
    public InputStream getCedulaverde() {
        return cedulaverde;
    }

    //SETTERS
    public void setIddocumentacion(int iddocumentacion) {
        this.iddocumentacion = iddocumentacion;
    }
    public void setFotofrontal(InputStream fotofrontal) {
        this.fotofrontal = fotofrontal;
    }
    public void setFototrasera(InputStream fototrasera) {
        this.fototrasera = fototrasera;
    }
    public void setFotolateraluno(InputStream fotolateraluno) {
        this.fotolateraluno = fotolateraluno;
    }
    public void setFotolateraldos(InputStream fotolateraldos) {
        this.fotolateraldos = fotolateraldos;
    }
    public void setFototecho(InputStream fototecho) {
        this.fototecho = fototecho;
    }
    public void setCedulaverde(InputStream cedulaverde) {
        this.cedulaverde = cedulaverde;
    }

    //METODOS
    
}

