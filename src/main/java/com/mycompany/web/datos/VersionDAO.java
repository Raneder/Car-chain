package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Modelo;
import com.mycompany.web.modelo.Version;

public class VersionDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Version> listar() {
        ArrayList<Version> versiones = new ArrayList<Version>();
        String sql = "SELECT * FROM version";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER EL MODELO ASOCIADO
                Modelo modelo = new Modelo();
                modelo.setIdmodelo(rs.getInt("modelo_id"));

                //OBTENER LA VERSION
                Version version = new Version();
                version.setIdversion(rs.getInt("idversion"));
                version.setNombreversion(rs.getString("nombreversion"));
                version.setDescripcionversion(rs.getString("descripcionversion"));
                version.setPreciomercado(rs.getDouble("preciomercado"));
                version.setPreciomercadognc(rs.getDouble("preciomercadognc"));
                version.setModelo(modelo);

                //AÑADIR VERSION A LA LISTA
                versiones.add(version);
            }
        } 
        catch (SQLException e) {
            System.out.println("aqui está el problema");
        }
        return versiones;
    }

    public Version getVersion(int id){
        Version version = new Version();
        String sql = "SELECT * FROM version WHERE idversion=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
            //OBTENER EL MODELO ASOCIADO
            Modelo modelo = new Modelo();
            modelo.setIdmodelo(rs.getInt("modelo_id"));

            //OBTENER LA VERSION
            version.setIdversion(id);
            version.setNombreversion(rs.getString("nombreversion"));
            version.setDescripcionversion(rs.getString("descripcionversion"));
            version.setPreciomercado(rs.getDouble("preciomercado"));
            version.setPreciomercadognc(rs.getDouble("preciomercadognc"));
            version.setModelo(modelo);
            }
        }
        catch ( SQLException e){
            System.out.println("getVersion No funciona");
        }
        return version;
    }
    
}
