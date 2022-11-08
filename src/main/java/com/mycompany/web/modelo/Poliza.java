package com.mycompany.web.modelo;

import java.util.Date;
import java.util.Calendar;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class Poliza {
    private int numeropoliza;
    private Usuario usuario;
    private Documentacion documentacion;
    private Linea_Cotizacion linea_cotizacion;
    private Periodo_De_Pago periodo_pago;
    private Tipo_De_Contratacion tipo_contratacion;
    private double preciopolizaactual;
    private double montoasegurado;
    private Date fec_cont_poliza;
    private LocalTime hora_cont_poliza;
    private Date fec_venc_poliza;
    private Date fec_canc_poliza;
    private boolean auto_renov_poliza;
    private Estado_Poliza estadopoliza;

    //CONSTRUCTORES
    public Poliza(){

    }
    public Poliza(Documentacion documentacion, Linea_Cotizacion linea_cotizacion){
        this.numeropoliza = 0;
        this.usuario = null;
        this.documentacion = documentacion;
        this.linea_cotizacion = linea_cotizacion;
        this.periodo_pago = null;
        this.tipo_contratacion = null;
        this.preciopolizaactual = linea_cotizacion.getMonto();
        if(linea_cotizacion.getCotizacion().getVehiculo().isGnc()){
            this.montoasegurado = linea_cotizacion.getCotizacion().getVehiculo().getVersion().getPreciomercadognc();
        }else{
            this.montoasegurado = linea_cotizacion.getCotizacion().getVehiculo().getVersion().getPreciomercado();
        }
        this.fec_cont_poliza = null;
        this.hora_cont_poliza = null;
        this.fec_venc_poliza = null;
        this.fec_canc_poliza = null;
        this.auto_renov_poliza = false;
        this.estadopoliza = Estado_Poliza.PENDIENTE;
    }
    public Poliza(int numeropoliza, Usuario usuario, Documentacion documentacion, Linea_Cotizacion linea_cotizacion,
            Periodo_De_Pago periodo_pago, Tipo_De_Contratacion tipo_contratacion, double preciopolizaactual,
            double montoasegurado, Date fec_cont_poliza, LocalTime hora_cont_poliza, Date fec_venc_poliza,
            Date fec_canc_poliza, boolean auto_renov_poliza, Estado_Poliza estadopoliza) {
        this.numeropoliza = numeropoliza;
        this.usuario = usuario;
        this.documentacion = documentacion;
        this.linea_cotizacion = linea_cotizacion;
        this.periodo_pago = periodo_pago;
        this.tipo_contratacion = tipo_contratacion;
        this.preciopolizaactual = preciopolizaactual;
        this.montoasegurado = montoasegurado;
        this.fec_cont_poliza = fec_cont_poliza;
        this.hora_cont_poliza = hora_cont_poliza;
        this.fec_venc_poliza = fec_venc_poliza;
        this.fec_canc_poliza = fec_canc_poliza;
        this.auto_renov_poliza = auto_renov_poliza;
        this.estadopoliza = estadopoliza;
    }

    //GETTERS
    public int getNumeropoliza() {
        return numeropoliza;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Documentacion getDocumentacion() {
        return documentacion;
    }
    public Linea_Cotizacion getLinea_cotizacion() {
        return linea_cotizacion;
    }
    public Periodo_De_Pago getPeriodo_pago() {
        return periodo_pago;
    }
    public Tipo_De_Contratacion getTipo_contratacion() {
        return tipo_contratacion;
    }
    public double getPreciopolizaactual() {
        return preciopolizaactual;
    }
    public double getMontoasegurado() {
        return montoasegurado;
    }
    public Date getFec_cont_poliza() {
        return fec_cont_poliza;
    }
    public LocalTime getHora_cont_poliza() {
        return hora_cont_poliza;
    }
    public Date getFec_venc_poliza() {
        return fec_venc_poliza;
    }
    public Date getFec_canc_poliza() {
        return fec_canc_poliza;
    }
    public boolean isAuto_renov_poliza() {
        return auto_renov_poliza;
    }
    public Estado_Poliza getEstadopoliza() {
        return estadopoliza;
    }

    //SETTERS
    public void setNumeropoliza(int numeropoliza) {
        this.numeropoliza = numeropoliza;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setDocumentacion(Documentacion documentacion) {
        this.documentacion = documentacion;
    }
    public void setLinea_cotizacion(Linea_Cotizacion linea_cotizacion) {
        this.linea_cotizacion = linea_cotizacion;
    }
    public void setPeriodo_pago(Periodo_De_Pago periodo_pago) {
        this.periodo_pago = periodo_pago;
    }
    public void setTipo_contratacion(Tipo_De_Contratacion tipo_contratacion) {
        this.tipo_contratacion = tipo_contratacion;
    }
    public void setPreciopolizaactual(double preciopolizaactual) {
        this.preciopolizaactual = preciopolizaactual;
    }
    public void setMontoasegurado(double montoasegurado) {
        this.montoasegurado = montoasegurado;
    }
    public void setFec_cont_poliza(Date fec_cont_poliza) {
        this.fec_cont_poliza = fec_cont_poliza;
    }
    public void setHora_cont_poliza(LocalTime hora_cont_poliza) {
        this.hora_cont_poliza = hora_cont_poliza;
    }
    public void setFec_venc_poliza(Date fec_venc_poliza) {
        this.fec_venc_poliza = fec_venc_poliza;
    }
    public void setFec_canc_poliza(Date fec_canc_poliza) {
        this.fec_canc_poliza = fec_canc_poliza;
    }
    public void setAuto_renov_poliza(boolean auto_renov_poliza) {
        this.auto_renov_poliza = auto_renov_poliza;
    }
    public void setEstadopoliza(Estado_Poliza estadopoliza) {
        this.estadopoliza = estadopoliza;
    }
    
    //METODOS
    public void cargarFechaContratacion(){
        Calendar calendar1 = Calendar.getInstance();
        this.fec_cont_poliza = calendar1.getTime();
    }
    public void cargarHoraContratacion(){
        Calendar calendar2 = Calendar.getInstance();
        Instant instant = calendar2.toInstant();
        ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
        this.hora_cont_poliza = LocalTime.ofInstant(instant, zoneId);
    }
    public String obtenerFechaCont(){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.fec_cont_poliza);
                
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
    public String obtenerHoraCont(){
        try{
            //OBTENER MINUTO
            int min = this.hora_cont_poliza.getMinute();
            String min_txt = min + "";
            if(min < 10){   min_txt = "0" + min_txt;    }
            
            //OBTENER HORA
            int hora = this.hora_cont_poliza.getHour();
            String hora_txt = hora + "";
            if(hora < 10){   hora_txt = "0" + hora_txt;    }
                
            return hora_txt + ":" + min_txt;
        }
        catch(Exception e){
            return "_ _ : _ _";
        }
    }
    public String obtenerFechaVenc(){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.fec_venc_poliza);
                
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
    public String obtenerFechaCanc(){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.fec_canc_poliza);
                
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
    public String obtenerFechaInicio(){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.fec_venc_poliza);
            calendar.add(Calendar.DAY_OF_MONTH, -7);
            
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
    public void calcularProximoVencimiento(int cantidad) {
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.fec_venc_poliza);
            calendar.add(Calendar.MONTH, cantidad);
            
            this.fec_venc_poliza = calendar.getTime();
        }
        catch(Exception e){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, cantidad);
            
            this.fec_venc_poliza = calendar.getTime();
        }
    }

}