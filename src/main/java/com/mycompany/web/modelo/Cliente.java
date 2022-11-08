package com.mycompany.web.modelo;

import java.sql.Date;

public class Cliente extends Persona{
    private int idCliente;

    //CONSTRUCTORES
    public Cliente(){
        
    }
    public Cliente(int idPersona, Localidad localidad, String nombres, String apellidos, Date fechanacimiento,
            Tipo_De_Documento tipoDocumento, String documento, String domicilio, String email, String telefono,
            boolean sexo, String contraseña, int idCliente) {
        super(idPersona, localidad, nombres, apellidos, fechanacimiento, tipoDocumento, documento, domicilio, email,
                telefono, sexo, contraseña);
        this.idCliente = idCliente;
    }

    //GETTERS
    public int getIdCliente() {
        return idCliente;
    }

    //SETTERS
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    //METODOS
    public Persona obtenerPersona(){
        Persona persona = new Persona();
        persona.setIdPersona(getIdPersona());
        persona.setCorreo(getCorreo());
        persona.setContraseña(getContraseña());
        persona.setNombres(getNombres());
        persona.setapellidos(getApellidos());
        persona.setTipoDocumento(getTipoDocumento());
        persona.setDocumento(getDocumento());
        persona.setFechanacimiento(getFechanacimiento());
        persona.setTelefono(getTelefono());
        persona.setSexo(isSexo());
        persona.setLocalidad(getLocalidad());
        persona.setDomicilio(getDomicilio());
        return persona;
    }

}