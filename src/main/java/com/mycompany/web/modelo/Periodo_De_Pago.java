package com.mycompany.web.modelo;

public class Periodo_De_Pago {
    private int idperiodopago;
    private String nombreperiodopago;
    private int cantidadmesespago;
    private int descuentoperiodopago;

    //CONSTRUCTORES
    public Periodo_De_Pago(){
        this.idperiodopago=0;
        this.nombreperiodopago="";
        this.cantidadmesespago=0;
        this.descuentoperiodopago=0;
    }
    public Periodo_De_Pago(int idperiodopago, String nombreperiodopago, int cantidadmesespago,
            int descuentoperiodopago) {
        this.idperiodopago = idperiodopago;
        this.nombreperiodopago = nombreperiodopago;
        this.cantidadmesespago = cantidadmesespago;
        this.descuentoperiodopago = descuentoperiodopago;
    }

    //GETTERS
    public int getIdperiodopago() {
        return idperiodopago;
    }
    public String getNombreperiodopago() {
        if(this.nombreperiodopago.equals("")){
            return "-";
        }
        else{
            return nombreperiodopago;
        }
    }
    public int getCantidadmesespago() {
        return cantidadmesespago;
    }
    public int getDescuentoperiodopago() {
        return descuentoperiodopago;
    }

    //SETTERS
    public void setIdperiodopago(int idperiodopago) {
        this.idperiodopago = idperiodopago;
    }
    public void setNombreperiodopago(String nombreperiodopago) {
        this.nombreperiodopago = nombreperiodopago;
    }
    public void setCantidadmesespago(int cantidadmesespago) {
        this.cantidadmesespago = cantidadmesespago;
    }
    public void setDescuentoperiodopago(int descuentoperiodopago) {
        this.descuentoperiodopago = descuentoperiodopago;
    }

    //METODOS
    
}
