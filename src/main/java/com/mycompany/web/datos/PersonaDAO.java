package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.web.modelo.Persona;

public class PersonaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public int agregar(Persona persona) {
        int id = 0;
        String sql = "INSERT INTO persona (localidad_id, nombres, apellidos, fechanacimiento, "
        + "tipodocumento, documento, domicilio, correo, telefono, sexo, contraseña) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, persona.getLocalidad().getIdlocalidad());
            ps.setString(2, persona.getNombres());
            ps.setString(3, persona.getApellidos());
            ps.setDate(4, new Date(persona.getFechanacimiento().getTime()));
            ps.setString(5, persona.getTipoDocumento().name());
            ps.setString(6, persona.getDocumento());
            ps.setString(7, persona.getDomicilio());
            ps.setString(8, persona.getCorreo());
            ps.setString(9, persona.getTelefono());
            ps.setBoolean(10, persona.isSexo());
            ps.setString(11, persona.getContraseña());
            int affectedRows = ps.executeUpdate();
            id = obtenerId(ps, affectedRows);
            ps.close();
        }
        catch (SQLException e) {
            System.out.println("agregarPersona es el problema");
        }
        return id;
    }

    public boolean buscarCorreo(String correo){
        boolean encontrado = false;
        String sql = "SELECT * FROM persona p WHERE p.correo=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            while (rs.next()) {
                encontrado=true;
            }
        }
        catch (SQLException e) {
            System.out.println("buscarCorreo es el problema");
        }
        return encontrado;
    }

    private int obtenerId(PreparedStatement ps, int affectedRows) throws SQLException {
        int id = 0;
        if (affectedRows == 0) {
            throw new SQLException("La creación de Usuario ha fallado, no hay filas afectadas.");
        }
        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = Integer.parseInt(generatedKeys.getLong(1) + "");
            }
            else {
                throw new SQLException("La creación de Usuario ha fallado, no se obtuvo el ID.");
            }
        }
        return id;
    }

}
