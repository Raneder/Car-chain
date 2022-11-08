package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Tipo_De_Contratacion;

public class Tipo_De_ContratacionDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Tipo_De_Contratacion> listar(){
        ArrayList<Tipo_De_Contratacion> tipos_contratacion = new ArrayList<Tipo_De_Contratacion>();
        String sql = "SELECT * FROM tipocontratacion";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER LA TIPO CONTRATACION
                Tipo_De_Contratacion tipo_contratacion = new Tipo_De_Contratacion();
                tipo_contratacion.setIdtipocontratacion(rs.getInt("idtipocontratacion"));
                tipo_contratacion.setNombrecontratacion(rs.getString("nombrecontratacion"));
                tipo_contratacion.setCantidadmesescontratacion(rs.getInt("cantidadmesescontratacion"));
                
                tipos_contratacion.add(tipo_contratacion);
            }
        }
        catch ( SQLException e){
            System.out.println("listarTiposContratacion No funciona");
        }
        return tipos_contratacion;
    }

    public Tipo_De_Contratacion getTipoContratacion(int id){
        Tipo_De_Contratacion tipo_contratacion = new Tipo_De_Contratacion();
        String sql = "SELECT * FROM tipocontratacion WHERE idtipocontratacion=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER TIPO DE CONTRATACION
                tipo_contratacion.setIdtipocontratacion(id);
                tipo_contratacion.setNombrecontratacion(rs.getString("nombrecontratacion"));
                tipo_contratacion.setCantidadmesescontratacion(rs.getInt("cantidadmesescontratacion"));
            }
        }
        catch ( SQLException e){
            System.out.println("getTipoContratacion No funciona");
        }
        return tipo_contratacion;
    }
}
