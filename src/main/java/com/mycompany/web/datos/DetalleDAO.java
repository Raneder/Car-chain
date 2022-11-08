package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Detalle;

public class DetalleDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Detalle> listarActivos() {
        ArrayList<Detalle> detalles = new ArrayList<Detalle>();
        String sql = "SELECT * FROM detalle WHERE activodetalle=?";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setBoolean(1, true);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER EL DETALLE
                Detalle detalle = new Detalle();
                detalle.setIddetalle(rs.getInt("iddetalle"));
                detalle.setNombredetalle(rs.getString("nombredetalle"));
                detalle.setDescripciondetalle(rs.getString("descripciondetalle"));
                detalle.setPorcentajemiles(rs.getInt("porcentajemiles"));
                detalle.setMontofijo(rs.getDouble("montofijo"));
                detalle.setActivodetalle(true);

                //AGREGAR A LOS DETALLES
                detalles.add(detalle);
            }
        } 
        catch (SQLException e) {
            System.out.println("No hay detalles");
        }
        return detalles;
    }
    
}
