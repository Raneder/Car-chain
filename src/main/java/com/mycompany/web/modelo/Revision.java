package com.mycompany.web.modelo;

import java.sql.Date;
import java.sql.Time;

public class Revision {
    private int idrevision;
    private Poliza poliza;
    private Usuario usuario;
    private Date fecharevision;
    private Time horarevsion;

    //CONSTRUCTORES
    public Revision(int idrevision, Poliza poliza, Usuario usuario, Date fecharevision, Time horarevsion) {
        this.idrevision = idrevision;
        this.poliza = poliza;
        this.usuario = usuario;
        this.fecharevision = fecharevision;
        this.horarevsion = horarevsion;
    }
    
    //GETTERS
    public int getIdrevision() {
        return idrevision;
    }
    public Poliza getPoliza() {
        return poliza;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Date getFecharevision() {
        return fecharevision;
    }
    public Time getHorarevsion() {
        return horarevsion;
    }
    
    //SETTERS
    public void setIdrevision(int idrevision) {
        this.idrevision = idrevision;
    }
    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setFecharevision(Date fecharevision) {
        this.fecharevision = fecharevision;
    }
    public void setHorarevsion(Time horarevsion) {
        this.horarevsion = horarevsion;
    }

    //METODOS
    
}
