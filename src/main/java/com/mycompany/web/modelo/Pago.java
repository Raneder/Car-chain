package com.mycompany.web.modelo;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Pago {
    private int idpago;
    private int poliza_num;
    private double totalpago;
    private Date fechapago;
    private LocalTime horapago;
    
    //CONSTRUCTORES
    public Pago(int poliza_num, double totalpago) {
        this.idpago = 0;
        this.poliza_num = poliza_num;
        this.totalpago = totalpago;
        
        //CARGAR FECHA
        Calendar calendar1 = Calendar.getInstance();
        this.fechapago = calendar1.getTime();

        //CARGAR HORA
        Calendar calendar2 = Calendar.getInstance();
        Instant instant = calendar2.toInstant();
        ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
        this.horapago = LocalTime.ofInstant(instant, zoneId);
    }

    //GETTERS
    public int getIdpago() {
        return idpago;
    }
    public int getPoliza_num() {
        return poliza_num;
    }
    public double getTotalpago() {
        return totalpago;
    }
    public Date getFechapago() {
        return fechapago;
    }
    public LocalTime getHorapago() {
        return horapago;
    }
    
    //SETTERS
    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }
    public void setPoliza_num(int poliza_num) {
        this.poliza_num = poliza_num;
    }
    public void setTotalpago(double totalpago) {
        this.totalpago = totalpago;
    }
    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }
    public void setHorapago(LocalTime horapago) {
        this.horapago = horapago;
    }

    //METODOS
    
}
