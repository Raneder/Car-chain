package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Provincia;

public class ProvinciaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Provincia> listar() {
        ArrayList<Provincia> provincias = new ArrayList<Provincia>();
        String sql = "SELECT * FROM provincia";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Provincia provincia = new Provincia();
                provincia.setIdprovincia(rs.getInt("idprovincia"));
                provincia.setDescripcionprovincia(rs.getString("descripcionprovincia"));
                provincias.add(provincia);
            }
            rs.close();
            ps.close();
            cn.desconectar();
            con.close();
        } 
        catch (SQLException e) {
            System.out.println("aqui está el problema");
        }
        return provincias;
    }

    public Provincia getProvincia(int id) {
        Provincia provincia = new Provincia();
        String sql = "SELECT * FROM provincia WHERE idprovincia=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                //USUARIO
                provincia.setIdprovincia(id);
                provincia.setDescripcionprovincia(rs.getString("descripcionprovincia"));
            }
        } 
        catch (SQLException e) {
            System.out.println("getProvincia No funciona");
        }
        return provincia;
    }
    
}