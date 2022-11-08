package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Localidad;
import com.mycompany.web.modelo.Provincia;

public class LocalidadDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Localidad> listar() {
        ArrayList<Localidad> localidades = new ArrayList<Localidad>();
        String sql = "SELECT * FROM localidad";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                //PROVINCIA A LA QUE PERTENECE
                Provincia provincia = new Provincia();
                provincia.setIdprovincia(rs.getInt("provincia_id"));

                //LOCALIDAD
                Localidad localidad = new Localidad();
                localidad.setIdlocalidad(rs.getInt("idLocalidad"));
                localidad.setDescripcionlocalidad(rs.getString("descripcionlocalidad"));
                localidad.setCodigopostal(rs.getString("codigopostal"));
                localidad.setProvincia(provincia);

                //LOCALIDADES
                localidades.add(localidad);
            }
            rs.close();
            ps.close();
            cn.desconectar();
            con.close();
        } 
        catch (SQLException e) {
            System.out.println("aqui está el problema");
        }
        return localidades;
    }
    
    public Localidad getLocalidad(int id) {
        Localidad localidad = new Localidad();
        String sql = "SELECT * FROM localidad WHERE idLocalidad=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                //PROVINCIA
                Provincia provincia = new Provincia();
                provincia.setIdprovincia(rs.getInt("provincia_id"));

                //LOCALIDAD
                localidad.setIdlocalidad(id);
                localidad.setDescripcionlocalidad(rs.getString("descripcionlocalidad"));
                localidad.setCodigopostal(rs.getString("codigopostal"));
                localidad.setProvincia(provincia);
            }
        } 
        catch (SQLException e) {
            System.out.println("getLocalidad No funciona");
        }
        return localidad;
    }
}
