package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.web.modelo.Configuracion_De_Edad;

public class Configuracion_De_EdadDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Configuracion_De_Edad getConfigEdad(int edad){
        Configuracion_De_Edad configEdad = new Configuracion_De_Edad();
        String sql = "SELECT * FROM configuracionedad WHERE minimace<=? AND maximace>=? AND activoce=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, edad);
            ps.setInt(2, edad);
            ps.setBoolean(3, true);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER LA CONFIGURACION DE EDAD
                configEdad.setIdconfiguracionedad(rs.getInt("idconfigedad"));
                configEdad.setNombrece(rs.getString("nombrece"));
                configEdad.setMinimace(rs.getInt("minimace"));
                configEdad.setMaximace(rs.getInt("maximace"));
                configEdad.setDescuentoce(rs.getInt("descuentoce"));
                configEdad.setGananciace(rs.getInt("gananciace"));
                configEdad.setRecargoce(rs.getDouble("recargoce"));
                configEdad.setActivoce(true);
            }
        }
        catch ( SQLException e){
            System.out.println("No hay Configuracion Edad");
            return null;
        }
        return configEdad;
    }
}
