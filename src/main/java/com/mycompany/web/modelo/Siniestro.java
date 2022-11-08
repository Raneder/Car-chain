package com.mycompany.web.modelo;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;

public class Siniestro {
    private int idsiniestro;
    private Poliza poliza;
    private Usuario usuario;
    private Date fechasiniestro;
    private Time horasiniestro;
    private InputStream fotodenuncia;
    private InputStream fotovehiculo;
    private Estado_Siniestro estado_siniestro;

    //CONSTRUCTORES
    public Siniestro(int idsiniestro, Poliza poliza, Usuario usuario, Date fechasiniestro, Time horasiniestro,
            InputStream fotodenuncia, InputStream fotovehiculo, Estado_Siniestro estado_siniestro) {
        this.idsiniestro = idsiniestro;
        this.poliza = poliza;
        this.usuario = usuario;
        this.fechasiniestro = fechasiniestro;
        this.horasiniestro = horasiniestro;
        this.fotodenuncia = fotodenuncia;
        this.fotovehiculo = fotovehiculo;
        this.estado_siniestro = estado_siniestro;
    }

    //GETTERS
    public int getIdsiniestro() {
        return idsiniestro;
    }
    public Poliza getPoliza() {
        return poliza;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Date getFechasiniestro() {
        return fechasiniestro;
    }
    public Time getHorasiniestro() {
        return horasiniestro;
    }
    public InputStream getFotodenuncia() {
        return fotodenuncia;
    }
    public InputStream getFotovehiculo() {
        return fotovehiculo;
    }
    public Estado_Siniestro getEstado_siniestro() {
        return estado_siniestro;
    }

    //SETTERS
    public void setIdsiniestro(int idsiniestro) {
        this.idsiniestro = idsiniestro;
    }
    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setFechasiniestro(Date fechasiniestro) {
        this.fechasiniestro = fechasiniestro;
    }
    public void setHorasiniestro(Time horasiniestro) {
        this.horasiniestro = horasiniestro;
    }
    public void setFotodenuncia(InputStream fotodenuncia) {
        this.fotodenuncia = fotodenuncia;
    }
    public void setFotovehiculo(InputStream fotovehiculo) {
        this.fotovehiculo = fotovehiculo;
    }
    public void setEstado_siniestro(Estado_Siniestro estado_siniestro) {
        this.estado_siniestro = estado_siniestro;
    }

    //METODOS

}
