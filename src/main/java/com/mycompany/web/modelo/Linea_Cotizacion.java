package com.mycompany.web.modelo;

public class Linea_Cotizacion {
    private int idlineacotizacion;
    private double monto;
    private Cotizacion cotizacion;
    private Cobertura cobertura;

    //CONSTRUCTORES
    public Linea_Cotizacion(){
    }
    public Linea_Cotizacion(Cotizacion cotizacion, Cobertura cobertura) {
        this.cotizacion = cotizacion;
        this.cobertura = cobertura;
    }

    //GETTERS
    public int getIdlineacotizacion() {
        return idlineacotizacion;
    }
    public double getMonto() {
        return monto;
    }
    public Cotizacion getCotizacion() {
        return cotizacion;
    }
    public Cobertura getCobertura() {
        return cobertura;
    }

    //SETTERS
    public void setIdlineacotizacion(int idlineacotizacion) {
        this.idlineacotizacion = idlineacotizacion;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }
    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    //METODOS
    public void calcularMonto(Cobertura_Detalle cobertura_detalle){
        double total = 0;
        double montoVehiculo = 0;
        double multiplicador = 0;
        double acumulador = 0;

        //OBTENER EL COSTO DE VEHICULO
        if(cotizacion.getVehiculo().isGnc()){ 
            montoVehiculo = cotizacion.getVehiculo().getVersion().getPreciomercadognc();
        }
        else{                                       
            montoVehiculo = cotizacion.getVehiculo().getVersion().getPreciomercado(); 
        }

        //OBTENER EL MONTO BASE A PARTIR DE LOS DETALLES
        for(Detalle d : cobertura_detalle.getDetalles()){
            total = total + (montoVehiculo*d.getPorcentajemiles()/10000);
        }

        //OBTENER LOS MULTIPLICADORES
        double uno = 1;
        double cien = 100;
        multiplicador = uno - (cotizacion.getConfig_localidad().getDescuentocl()/cien);
        multiplicador = multiplicador * (uno - (cotizacion.getConfig_antiguedad().getDescuentoca()/cien));
        multiplicador = multiplicador * (uno - (cotizacion.getConfig_edad().getDescuentoce()/cien));
        multiplicador = multiplicador * (uno + (cotizacion.getConfig_localidad().getGananciacl()/cien));
        multiplicador = multiplicador * (uno + (cotizacion.getConfig_antiguedad().getGananciaca()/cien));
        multiplicador = multiplicador * (uno + (cotizacion.getConfig_edad().getGananciace()/cien));

        //OBTENER TODOS LOS SUMADORES
        acumulador = cotizacion.getConfig_edad().getRecargoce();
        acumulador = acumulador + cotizacion.getConfig_localidad().getRecargocl();
        acumulador = acumulador + cotizacion.getConfig_antiguedad().getRecargoca();

        this.monto = total*multiplicador + acumulador;
    }

}