package com.mycompany.web.modelo;

import java.sql.Date;

public class Usuario extends Persona{
    private String legajo;
    private boolean estado;
    private Tipo_De_Usuario tipoUsuario;

    //CONSTRUCTORES
    public Usuario(){
        
    }
    public Usuario(int idPersona, Localidad localidad, String nombres, String apellidos, Date fechanacimiento,
            Tipo_De_Documento tipoDocumento, String documento, String domicilio, String email, String telefono,
            boolean sexo, String contraseña, String legajo, boolean estado, Tipo_De_Usuario tipoUsuario) {
        super(idPersona, localidad, nombres, apellidos, fechanacimiento, tipoDocumento, documento, domicilio, email,
                telefono, sexo, contraseña);
        this.legajo = legajo;
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
    }

    //GETTERS
    public String getLegajo() {
        return legajo;
    }
    public boolean isEstado() {
        return estado;
    }
    public Tipo_De_Usuario getTipoUsuario() {
        return tipoUsuario;
    }

    //SETTERS
    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public void setTipoUsuario(Tipo_De_Usuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    //TOSTRING
    public String mostrarUsuario() {
        return "----Usuario---- \nlegajo=" + legajo + ", \nestado=" + estado + ", \ntipoUsuario=" + tipoUsuario;
    }
    
    //METODOS
    
}
