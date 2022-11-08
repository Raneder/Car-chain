package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.web.modelo.Cliente;
import com.mycompany.web.modelo.Localidad;
import com.mycompany.web.modelo.Tipo_De_Documento;

public class ClienteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Cliente clienteExiste(String correo, String contraseña) {
        Cliente cliente = new Cliente();
        String sql =    "SELECT * FROM cliente c, persona p "+
                        "WHERE c.persona_id = p.idpersona AND p.correo=? AND p.contraseña=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            while (rs.next()) {
                //LOCALIDAD
                Localidad localidad = new Localidad();
                localidad.setIdlocalidad(rs.getInt("localidad_id"));

                //CLIENTE
                cliente.setIdCliente(rs.getInt("idcliente"));
                
                //DATOS DE PERSONA EN CLIENTE
                cliente.setIdPersona(rs.getInt("idpersona"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setapellidos(rs.getString("apellidos"));
                cliente.setFechanacimiento(rs.getDate("fechanacimiento"));
                Tipo_De_Documento tipo_documento = Tipo_De_Documento.obtenerTipo(rs.getString("tipodocumento"));
                cliente.setTipoDocumento(tipo_documento);
                cliente.setDocumento(rs.getString("documento"));
                cliente.setDomicilio(rs.getString("domicilio"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setSexo(rs.getBoolean("sexo"));
                cliente.setContraseña(rs.getString("contraseña"));
                cliente.setLocalidad(localidad);
            }
        }
        catch (SQLException e) {
            System.out.println("clienteExiste es el problema");
        }
        return cliente;
    }

    public int agregar(Cliente cliente) {
        int id = 0;
        String sql = "INSERT INTO cliente (persona_id) VALUES (?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cliente.getIdPersona());
            int affectedRows = ps.executeUpdate();
            id = obtenerId(ps, affectedRows);
        }
        catch (SQLException e) {
            System.out.println("agregarCliente es el problema");
        }

        return id;
    }

    public Cliente getCliente(int id) {
        Cliente cliente = new Cliente();
        String sql =    "SELECT * FROM cliente c, persona p "+
                        "WHERE c.persona_id = p.idpersona AND c.idcliente=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                //LOCALIDAD
                Localidad localidad = new Localidad();
                localidad.setIdlocalidad(rs.getInt("localidad_id"));

                //CLIENTE
                cliente.setIdCliente(rs.getInt("idcliente"));
                
                //DATOS DE PERSONA EN CLIENTE
                cliente.setIdPersona(rs.getInt("idpersona"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setapellidos(rs.getString("apellidos"));
                cliente.setFechanacimiento(rs.getDate("fechanacimiento"));
                Tipo_De_Documento tipo_documento = Tipo_De_Documento.obtenerTipo(rs.getString("tipodocumento"));
                cliente.setTipoDocumento(tipo_documento);
                cliente.setDocumento(rs.getString("documento"));
                cliente.setDomicilio(rs.getString("domicilio"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setSexo(rs.getBoolean("sexo"));
                cliente.setContraseña(rs.getString("contraseña"));
                cliente.setLocalidad(localidad);
            }
        }
        catch (SQLException e) {
            System.out.println("getCliente es el problema");
        }
        return cliente;
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
