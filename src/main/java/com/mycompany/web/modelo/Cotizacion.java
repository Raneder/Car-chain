package com.mycompany.web.modelo;

import java.util.Date;
import java.util.Calendar;

public class Cotizacion {
    private int idcotizacion;
    private Vehiculo vehiculo;
    private Configuracion_De_Localidad config_localidad;
    private Configuracion_De_Edad config_edad;
    private Configuracion_De_Antiguedad config_antiguedad;
    private Date fecha_creacion;
    private Date fecha_vencimiento;

    //CONSTRUCTORES
    public Cotizacion(){

    }
    public Cotizacion(int idcotizacion, Vehiculo vehiculo, Configuracion_De_Localidad config_localidad,
            Configuracion_De_Edad config_edad, Configuracion_De_Antiguedad config_antiguedad) {
        this.idcotizacion = idcotizacion;
        this.vehiculo = vehiculo;
        this.config_localidad = config_localidad;
        this.config_edad = config_edad;
        this.config_antiguedad = config_antiguedad;
        cargarFechaCreacion();
        cargarFechaVencimiento();
    }

    //GETTERS
    public int getIdcotizacion() {
        return idcotizacion;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public Configuracion_De_Localidad getConfig_localidad() {
        return config_localidad;
    }
    public Configuracion_De_Edad getConfig_edad() {
        return config_edad;
    }
    public Configuracion_De_Antiguedad getConfig_antiguedad() {
        return config_antiguedad;
    }
    public Date getFecha_creacion() {
        return fecha_creacion;
    }
    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    //SETTERS
    public void setIdcotizacion(int idcotizacion) {
        this.idcotizacion = idcotizacion;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    public void setConfig_localidad(Configuracion_De_Localidad config_localidad) {
        this.config_localidad = config_localidad;
    }
    public void setConfig_edad(Configuracion_De_Edad config_edad) {
        this.config_edad = config_edad;
    }
    public void setConfig_antiguedad(Configuracion_De_Antiguedad config_antiguedad) {
        this.config_antiguedad = config_antiguedad;
    }
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    //METODOS    
    public void cargarFechaCreacion() {
        Calendar calendar = Calendar.getInstance();
        this.fecha_creacion = calendar.getTime();
    }
    public void cargarFechaVencimiento() {
        Calendar calendar = Calendar.getInstance();
        switch(calendar.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.SATURDAY:
                calendar.add(Calendar.DAY_OF_WEEK, 9);
                break;
            case Calendar.SUNDAY:
                calendar.add(Calendar.DAY_OF_WEEK, 8);
                break;
            default:
                calendar.add(Calendar.DAY_OF_WEEK, 7);
                break;
        }
        this.fecha_vencimiento = calendar.getTime();
    }
    public String obtenerFechaCreacion(){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.fecha_creacion);
                
            //OBTENER DIA
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            String dia_txt = dia + "";
            if(dia < 10){   dia_txt = "0" + dia_txt;    }
                
            //OBTENER MES
            int mes = calendar.get(Calendar.MONTH) + 1;
            String mes_txt = mes + "";
            if(mes < 10){   mes_txt = "0" + mes_txt;    }

            //OBTENER AÑO
            int año = calendar.get(Calendar.YEAR);

            return dia_txt + "/" + mes_txt + "/" + año;
        }
        catch(Exception e){
            return "_ _/_ _ /_ _ _ _";
        }
    }
}