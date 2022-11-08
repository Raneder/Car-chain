package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Marca;
import com.mycompany.web.modelo.Modelo;

public class ModeloDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Modelo> listar() {
        ArrayList<Modelo> modelos = new ArrayList<Modelo>();
        String sql = "SELECT * FROM modelo";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER LA MARCA ASOCIADA
                Marca marca = new Marca();
                marca.setIdmarca(rs.getInt("marca_id"));

                //OBTENER EL MODELO
                Modelo modelo = new Modelo();
                modelo.setIdmodelo(rs.getInt("idmodelo"));
                modelo.setNombremodelo(rs.getString("nombremodelo"));
                modelo.setDescripcionmodelo(rs.getString("descripcionmodelo"));
                modelo.setMarca(marca);

                //AÑADIR MODELO A LA LISTA
                modelos.add(modelo);
            }
        } 
        catch (SQLException e) {
            System.out.println("aqui está el problema");
        }
        return modelos;
    }
    
    public Modelo getModelo(int id){
        Modelo modelo = new Modelo();
        String sql = "SELECT * FROM modelo WHERE idmodelo=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER MARCA ASOCIADA
                Marca marca = new Marca();
                marca.setIdmarca(rs.getInt("marca_id"));

                //OBTENER MODELO
                modelo.setIdmodelo(id);
                modelo.setNombremodelo(rs.getString("nombremodelo"));
                modelo.setDescripcionmodelo(rs.getString("descripcionmodelo"));
                modelo.setMarca(marca);
            }
        }
        catch ( SQLException e){
            System.out.println("getModelo No funciona");
        }
        return modelo;
    }
}
