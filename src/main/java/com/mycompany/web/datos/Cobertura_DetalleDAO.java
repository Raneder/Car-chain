package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Cobertura;
import com.mycompany.web.modelo.Detalle;
import com.mycompany.web.modelo.Cobertura_Detalle;

public class Cobertura_DetalleDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Cobertura_Detalle listar(Cobertura cobertura) {
        Cobertura_Detalle cobertura_detalle = new Cobertura_Detalle();
        cobertura_detalle.setCobertura(cobertura);
        
        String sql = "SELECT * FROM coberturadetalle cd, cobertura c, detalle d WHERE "
        +"cd.cobertura_id=? AND cd.cobertura_id=c.idcobertura AND cd.detalle_id=d.iddetalle";

        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cobertura.getIdcobertura());
            rs=ps.executeQuery();
            ArrayList<Detalle> detalles = new ArrayList<Detalle>();
            while (rs.next()){
                //OBTENER EL DETALLE
                Detalle detalle = new Detalle();
                detalle.setIddetalle(rs.getInt("d.iddetalle"));
                detalle.setNombredetalle(rs.getString("d.nombredetalle"));
                detalle.setDescripciondetalle(rs.getString("d.descripciondetalle"));
                detalle.setPorcentajemiles(rs.getInt("d.porcentajemiles"));
                detalle.setMontofijo(rs.getDouble("d.montofijo"));
                detalle.setActivodetalle(true);

                //AGREGAR A LOS DETALLES
                detalles.add(detalle);
            }
            cobertura_detalle.setDetalles(detalles);
        } 
        catch (SQLException e) {
            System.out.println("CoberturaDetalle No Funciona");
        }
        return cobertura_detalle;
    }

    public boolean cobertura_detalle_existe(Cobertura cobertura, Detalle detalle){
        boolean llave = false;
        String sql = "SELECT * FROM coberturadetalle WHERE cobertura_id=? AND detalle_id=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cobertura.getIdcobertura());
            ps.setInt(2, detalle.getIddetalle());
            rs=ps.executeQuery();
            while (rs.next()){
                llave = true;
            }
        }
        catch (SQLException e){
            System.out.println("cobertura_detalle_existe No Funciona");
        }
        return llave;
    }
}
