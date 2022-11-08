package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.web.modelo.Configuracion_De_Localidad;
import com.mycompany.web.modelo.Localidad;

public class Configuracion_De_LocalidadDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Configuracion_De_Localidad getConfigLocalidad(Localidad localidad){
        Configuracion_De_Localidad configLocalidad = new Configuracion_De_Localidad();
        String sql = "SELECT * FROM configuracionlocalidad WHERE localidad_id=? AND activocl=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, localidad.getIdlocalidad());
            ps.setBoolean(2, true);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER LA CONFIGURACION DE LOCALIDAD
                configLocalidad.setIdconfiglocalidad(rs.getInt("idconfiglocalidad"));
                configLocalidad.setLocalidad(localidad);
                configLocalidad.setNombrecl(rs.getString("nombrecl"));
                configLocalidad.setDescuentocl(rs.getInt("descuentocl"));
                configLocalidad.setGananciacl(rs.getInt("gananciacl"));
                configLocalidad.setRecargocl(rs.getDouble("recargocl"));
                configLocalidad.setActivocl(true);
            }
        }
        catch ( SQLException e){
            System.out.println("No hay Configuracion Localidad");
            return null;
        }
        return configLocalidad;
    }
}
