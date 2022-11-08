package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Marca;

public class MarcaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Marca> listar() {
        ArrayList<Marca> marcas = new ArrayList<Marca>();
        String sql = "SELECT * FROM marca";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER MARCA
                Marca marca = new Marca();
                marca.setIdmarca(rs.getInt("idmarca"));
                marca.setNombremarca(rs.getString("nombremarca"));
                marca.setDescripcionmarca(rs.getString("descripcionmarca"));

                //AÑADIR MARCA A LISTA
                marcas.add(marca);
            }
        } 
        catch (SQLException e) {
            System.out.println("aqui está el problema");
        }
        return marcas;
    }
    
    public Marca getMarca(int id){
        Marca marca = new Marca();
        String sql = "SELECT * FROM marca WHERE idmarca=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER LA MARCA
                marca.setIdmarca(id);
                marca.setNombremarca(rs.getString("nombremarca"));
                marca.setDescripcionmarca(rs.getString("descripcionmarca"));
            }
        }
        catch ( SQLException e){
            System.out.println("getMarca No funciona");
        }
        return marca;
    }
}
