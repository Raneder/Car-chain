package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Cobertura;

public class CoberturaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Cobertura> listarActivos() {
        ArrayList<Cobertura> coberturas = new ArrayList<Cobertura>();
        String sql = "SELECT * FROM cobertura WHERE activocobertura=?";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setBoolean(1, true);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER EL DETALLE
                Cobertura cobertura = new Cobertura();
                cobertura.setIdcobertura(rs.getInt("idcobertura"));
                cobertura.setNombrecobertura(rs.getString("nombrecobertura"));
                cobertura.setDescripcioncobertura(rs.getString("descripcioncobertura"));
                cobertura.setRecargoporatraso(rs.getInt("recargoporatraso"));
                cobertura.setActivocobertura(true);

                //AGREGAR A LOS DETALLES
                coberturas.add(cobertura);
            }
        } 
        catch (SQLException e) {
            System.out.println("No hay detalles");
        }
        return coberturas;
    }

    
    public Cobertura getCobertura(int id){
        Cobertura cobertura = new Cobertura();
        String sql = "SELECT * FROM cobertura WHERE idcobertura=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){                
                cobertura.setIdcobertura(id);
                cobertura.setNombrecobertura(rs.getString("nombrecobertura"));
                cobertura.setDescripcioncobertura(rs.getString("descripcioncobertura"));
                cobertura.setRecargoporatraso(rs.getInt("recargoporatraso"));
                cobertura.setActivocobertura(rs.getBoolean("activocobertura"));
            }
        }
        catch ( SQLException e){
            System.out.println("getCobertura No funciona");
        }
        return cobertura;
    }
    
}
